# Fetch Request Semantics

## Problem Statement

Suppose you have an existent service fetching records, and you need to expand how consumers can query those records. You already have a request envelope that, perhaps, specifies a few `Option` parameters that provide conditional filtering (we'll get specific in a moment).

You might think, "I'll add more optional parameters," and immediately, we notice a problem.

Are those parameters mutually exclusive? If so, where are the boundaries of that exclusivity? Consumers may need further clarification. `Option` has, sadly, become a widely overloaded concept with implications for everything from business logic to compatibility concerns.

Worse, how do we implement those queries? Pattern matching on a few `Option` parameters starts simple enough but doesn't scale. Worse, now we've made it easy to violate our invariants. If the consumer supplies a pair of exclusive parameters (or none at all), they've forced us to report errors that have nothing to do with our business domain but instead deal with how to use our service. What's considered a "bad request" should be narrowly defined, and we should make it difficult for our consumers to craft one.

This recurring mantra applies: **make confusing and invalid states difficult or impossible to represent.**

## Evolving Solution

How, exactly, do we achieve that—making invalid states impossible to represent? Let's walk through a naïve approach and iterate our way to a more optimal solution.

### Setup

Suppose we have a service for fetching users:

https://github.com/michaelahlers/scala-examples/blob/4fbf006008d1248df958d9aeca6cfb2dfbef4897/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/UserService.scala#L3-L5

That already searches by their given and family names:

https://github.com/michaelahlers/scala-examples/blob/4fbf006008d1248df958d9aeca6cfb2dfbef4897/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/GetUsersRequest.scala#L3-L6

Even before exploring further, we already see deficiencies:

https://github.com/michaelahlers/scala-examples/blob/4fbf006008d1248df958d9aeca6cfb2dfbef4897/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/InterfaceDesignImprovementsSetupApp.scala#L7-L26

It seems trivial, but what happens if we need more properties, like a user's email address, phone number, city, and region? We've now conflated more positive identifiers (a user's name and contact information) with demographic information (their locale) in the same query type.

With more `Option` parameters, the interface and implementation get more confusing:

```scala
case class GetUsersRequest(
  givenName: Option[String],
  familyName: Option[String],
  emailAddress: Option[String],
  phoneNumber: Option[String],
  city: Option[String],
  region: Option[String],
)
```

Now, we (and our service's consumers) must wonder about our query's inclusivity. Is `None` considered a wildcard? What if that returns too many results? And how can we expect to use this? Are we casting a wider net or being more specific? (Email addresses are reliable and durable identifiers, but phone numbers are neither.) Never mind how this relates to optionality in the database. Is this discovery? Do we know who we're looking for? Of course, documentation might address any confusion (which everyone reads, right?), but we can do much better.

### First Alternative

Same service as before:

https://github.com/michaelahlers/scala-examples/blob/4fbf006008d1248df958d9aeca6cfb2dfbef4897/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/alternative1/UserService.scala#L3-L5

Before examining our revised request type, let's make a replacement for `Option` (and all the ambiguities it confers) by modeling how our arguments ought to match:

https://github.com/michaelahlers/scala-examples/blob/4fbf006008d1248df958d9aeca6cfb2dfbef4897/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/alternative1/Argument.scala#L6-L13

Now, we find ourselves with a `GetUsersRequest` that's expressive and intuitive:

https://github.com/michaelahlers/scala-examples/blob/4fbf006008d1248df958d9aeca6cfb2dfbef4897/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/alternative1/GetUsersRequest.scala#L6-L19

Which makes our use cases abundantly clear:

https://github.com/michaelahlers/scala-examples/blob/4fbf006008d1248df958d9aeca6cfb2dfbef4897/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/alternative1/InterfaceDesignImprovementsAlternative1App.scala#L9-L35

We've already achieved some valuable improvements for ourselves and the service's consumers:

1. Query semantics are precise—the ambiguities caused by using `Option` are replaced with clear and concise per-property matching behavior.
1. Each query explicitly and unambiguously states what question it's answering.
1. The implementation is outside the scope of this subject, but whatever it is, it's undoubtedly more straightforward, needing to handle only three cases.

However, there are drawbacks:

1. While the implementation work mentioned earlier is easier, our service is far less flexible than it could be.
1. It's incumbent upon consumers to call the service repeatedly to obtain either a union or intersection of the results.

Here is a scenario where we deal more with tradeoffs than solutions. (Understanding and choosing between tradeoffs is an essential responsibility of our profession as software engineers.) But this pattern, overall, might be more than enough for most teams. It remains applicable for and inspires a narrow set of supported use cases that fit specific business or customer needs. (_I.e._, alternativeing to provide a generic query interface might be counterproductive to your requirements.)

While we could stop here, what if we wanted to get fancier and provide more flexibility? How would that look?
