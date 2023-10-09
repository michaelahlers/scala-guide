# Better Interface Design with Types

## Preamble

We can bring [Algebraic Data Types][glossary-algebraic-data-types] to bear on API design—they're not inherently limited to modeling business domains. Even if that seems obvious, I've found developers can miss out on their benefits: how they can precisely clarify your interface's intent and implementation behavior.

Here, we'll explore a hypothetical situation where we want to increase the capabilities of a service and help consumers better understand how to use it.

## Topics Covered

- Service API design
- [Algebraic Data Types][glossary-algebraic-data-types]
- [Type Parameter Covariance](https://docs.scala-lang.org/tour/variances.html)

[glossary-algebraic-data-types]: ../../../glossary/algebraic-data-types.md

## What problem are we solving?

Suppose you have a service for getting users and need to expand how consumers can query those users. You already have a request envelope specifying a few `Option` parameters to select those records.

Before we think, "I'll add more optional parameters," let's understand existing semantics.

Let's see the request:

```scala
case class GetUsersRequest(
  givenName: Option[String],
  familyName: Option[String],
)
```

We immediately have questions.

What does optionality mean here? Is `None` a wildcard? Does it match on absence? If `Some`, does it match exactly? On a substring? Or something else?

Are we forming a union or intersection query? (_I.e._, is it `givenName` _and_ `familyName`, or `givenName` _and_ `familyName`?)

What happens when both parameters are `None`? Do we get no users or all of them? If we get no users, that presents a surprising special case.

`Option` has, sadly, become a widely overloaded concept with implications for everything from business logic to compatibility concerns.

Next, how do we implement those queries? Pattern matching on a few `Option` parameters starts simple enough but doesn't scale. If we add more, we're going to have difficulty.

Especially if there's any exclusivity between the new parameters we add. For example, if we throw in an `emailAddress: Option[String]`, we now have a property to identify a specific user! How does it relate to existing properties? Are they exclusive, given the semantics? What if they disagree (_e.g._, the address identifies a specific user, but we gave the wrong name)?

Whatever decisions we make, it's become easy to violate our invariants. When that happens, our service will report errors that have nothing to do with our business domain but instead, deal with how to use our service. What's considered a "bad request" should be narrowly defined, and we should make it difficult for our consumers to craft one.

## Finding Solutions

How, exactly, do we achieve that—making invalid states impossible to represent? Let's walk through a naïve approach and iterate our way to a more optimal solution.

### Project Setup

Suppose we have a service for fetching users:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/UserService.scala#L3-L5

That already searches by their given and family names:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/GetUsersRequest.scala#L3-L6

Even before exploring further, we already see deficiencies:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/UsageApp.scala#L7-L26

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

### First Version

Same service as before:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version1/UserService.scala#L3-L5

Before examining our revised request type, let's make a replacement for `Option` (and all the ambiguities it confers) by modeling how our arguments ought to match:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version1/Expression.scala#L6-L13

Now, we find ourselves with a `GetUsersRequest` that's expressive and intuitive:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version1/GetUsersRequest.scala#L6-L19

Which makes our use cases abundantly clear:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version1/UsageApp.scala#L9-L35

We've already achieved some valuable improvements for ourselves and the service's consumers:

1. Query semantics are precise—the ambiguities caused by using `Option` are replaced with clear and concise per-property matching behavior.
1. Each query explicitly and unambiguously states what question it's answering.
1. The implementation is outside the scope of this subject, but whatever it is, it's undoubtedly more straightforward, needing to handle only three cases.

However, there are drawbacks:

1. While the implementation work mentioned earlier is easier, our service is far less flexible than it could be.
1. It's incumbent upon consumers to call the service repeatedly to obtain either a union or intersection of the results.

Here is a scenario where we deal more with tradeoffs than solutions. (Understanding and choosing between tradeoffs is an essential responsibility of our profession as software engineers.) But this pattern, overall, might be more than enough for most teams. It remains applicable for and inspires a narrow set of supported use cases that fit specific business or customer needs. (_I.e._, alternativeing to provide a generic query interface might be counterproductive to your requirements.)

While we could stop here, what if we wanted to get fancier and provide more flexibility? How would that look?

### Second Version

Let's state upfront: we probably don't want to do this. I'm giving this version to inspire your imagination and expand your knowledge. In practice, we'd reach for a different tool ([GraphQL][technology-graph-ql], as implemented by [Sangria][graph-ql-sangria] or [Caliban][graph-ql-zio-caliban] might be more appropriate). That said, let's dive in and flex our Scala muscles a bit!

[technology-graph-ql]: https://graphql.org/
[graph-ql-sangria]: https://github.com/sangria-graphql/sangria
[graph-ql-zio-caliban]: https://zio.dev/ecosystem/community/caliban/

Once again, the same service:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version2/UserService.scala#L3-L5

And _almost_ same expression (note the absence of a `Wildcard` case):

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version2/Expression.scala#L6-L12

But now we introduce composable predicates to our request envelope, which includes a lightweight syntax for easily composing our predicates:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version2/GetUsersRequest.scala#L10-L47

With our newfound expressive capabilities, we can describe virtually any query we want, answering all sorts of questions:

https://github.com/michaelahlers/scala-examples/blob/a013ad5fcd16106b313565dcda37051b69699966/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version2/UsageApp.scala#L10-L34

Nothing extra, total flexibility, and it's drop-dead easy to use. Our consumers can tell us exactly what they want in perpetuity with an elegant and simple API.

Not so fast.

Could this enable consumers to craft expensive queries? Possibly. In the theme of a favorite trope of "preventing invalid states," we show here how additional types suggest how specific properties are indexed. Also, we've introduced the use of `NonEmptyList` from the widely popular [Cats][technology-typelevel-cats] library to enforce at minimum one predicate for our boolean operators.

It could at least make optimizing our most common use cases more difficult. We could always revisit making our request type an ADT and spelling out those optimized cases while adding another that accepts a `Predicate` as described here.

These are exercises for the reader.

[technology-typelevel-cats]: https://typelevel.org/cats/

## Conclusion

We've seen how Algebraic Data Types can help us design better interfaces. We've also suggested how they can make implementing those interfaces easier. Of course, we've also seen how they can make confusing or invalid states unrepresentable.

Also, note that the Scala compiler will catch mistakes at implementation. Should your cases change (whether by addition, update, or removal), it will be impossible—thanks to the `sealed` keyword attached to the ADT traits involved—to miss subsequent code changes to suit.

Your specific needs will be entirely different from this contrived example, but as you design your next API using Scala, consider how these techniques might help make its use self-documenting and bulletproof against unintentional use.
