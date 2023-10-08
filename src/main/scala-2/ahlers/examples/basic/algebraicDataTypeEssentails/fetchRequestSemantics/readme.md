# Fetch Request Semantics

## Problem Statement

Suppose you have an existent service fetching records, and you need to expand how consumers can query those records. You already have a request envelope that, perhaps, specifies a few `Option` parameters that provide conditional filtering (we'll get specific in a moment).

You might think, "I'll add more optional parameters," and immediately, we notice a problem.

Are those parameters mutually exclusive? If so, where are the boundaries of that exclusivity? Consumers may need further clarification. `Option` has, sadly, become a widely overloaded concept with implications for everything from business logic to compatibility concerns.

Worse, how do we implement those queries? Pattern matching on a few `Option` parameters starts simple enough but doesn't scale. Worse, now we've made it easy to violate our invariants. If the consumer supplies a pair of exclusive parameters (or none at all), they've forced us to report errors that have nothing to do with our business domain but instead deal with how to use our service. What's considered a "bad request" should be narrowly defined, and we should make it difficult for our consumers to craft one.

This recurring mantra applies: **make invalid states impossible to represent.**

## Evolving Solution

How, exactly, do we achieve that—making invalid states impossible to represent? Let's walk through a naïve approach and iterate our way to a more optimal solution.

### Setup

Suppose we have a service for fetching users:

https://github.com/michaelahlers/scala-examples/blob/6cb84888113c13f4d260c6778d8928f0d11ff453/src/main/scala-2/ahlers/examples/basic/algebraicDataTypeEssentails/fetchRequestSemantics/setup/UserService.scala#L3-L5

That already searches by their given and family names:

https://github.com/michaelahlers/scala-examples/blob/6cb84888113c13f4d260c6778d8928f0d11ff453/src/main/scala-2/ahlers/examples/basic/algebraicDataTypeEssentails/fetchRequestSemantics/setup/GetUsersRequest.scala#L3-L6

Even before exploring further, we already see deficiencies.

https://github.com/michaelahlers/scala-examples/blob/6cb84888113c13f4d260c6778d8928f0d11ff453/src/main/scala-2/ahlers/examples/basic/algebraicDataTypeEssentails/fetchRequestSemantics/setup/FetchRequestSemanticsSetupApp.scala#L7-L12

https://github.com/michaelahlers/scala-examples/blob/20ec6a9f0ebab855e35228eff039771b2bd16b34/src/main/scala-2/ahlers/examples/basic/algebraicDataTypeEssentails/fetchRequestSemantics/setup/FetchRequestSemanticsSetupApp.scala#L14-L19

https://github.com/michaelahlers/scala-examples/blob/6cb84888113c13f4d260c6778d8928f0d11ff453/src/main/scala-2/ahlers/examples/basic/algebraicDataTypeEssentails/fetchRequestSemantics/setup/FetchRequestSemanticsSetupApp.scala#L21-L26

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

Now, we're wondering about our query's inclusivity (and so are our service's consumers). Are we casting a wider net or being more specific? Email addresses are reliable and durable identifiers, but phone numbers are neither. Is this discovery? Do we know who we're looking for? Of course, documentation could address any confusion (which everyone reads, right?), but we can do much better.
