# Interface Design Improvements

We can bring Algebraic Data Types to bear on API design—they're not inherently limited to modeling business domains. Here, we'll explore a hypothetical situation where we want to increase the capabilities of a service and help consumers better understand how to use it.

## What problem are we solving?

Suppose you have a service for fetching records, and you need to expand how consumers can query those records. You already have a request envelope that, perhaps, specifies a few `Option` parameters that provide conditional filtering (we'll get specific in a moment).

You might think, "I'll add more optional parameters," and immediately, we notice a problem.

How do those parameters relate to and affect one another? Are there any that are mutually exclusive? If so, where are the boundaries of that exclusivity? Consumers may need further clarification. `Option` has, sadly, become a widely overloaded concept with implications for everything from business logic to compatibility concerns.

Worse, how do we implement those queries? Pattern matching on a few `Option` parameters starts simple enough but doesn't scale. Worse, now we've made it easy to violate our invariants. If the consumer supplies a pair of exclusive parameters (or none at all), they've forced us to report errors that have nothing to do with our business domain but instead deal with how to use our service. What's considered a "bad request" should be narrowly defined, and we should make it difficult for our consumers to craft one.

This recurring mantra applies: **make confusing and invalid states difficult or impossible to represent.**

## Finding Solutions

How, exactly, do we achieve that—making invalid states impossible to represent? Let's walk through a naïve approach and iterate our way to a more optimal solution.

### Setup

Suppose we have a service for fetching users:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/UserService.scala#L3-L5

That already searches by their given and family names:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/GetUsersRequest.scala#L3-L6

Even before exploring further, we already see deficiencies:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/setup/InterfaceDesignImprovementsSetupApp.scala#L7-L26

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

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version1/UserService.scala#L3-L5

Before examining our revised request type, let's make a replacement for `Option` (and all the ambiguities it confers) by modeling how our arguments ought to match:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version1/Argument.scala#L6-L13

Now, we find ourselves with a `GetUsersRequest` that's expressive and intuitive:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version1/GetUsersRequest.scala#L6-L19

Which makes our use cases abundantly clear:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version1/InterfaceDesignImprovementsVersion1App.scala#L9-L35

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

Once again, the same service and same expression types:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version2/UserService.scala#L3-L5

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version2/Expression.scala#L6-L13

But now we introduce composable predicates to our request envelope:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version2/GetUsersRequest.scala#L8-L37

With our newfound expressive capabilities, we can describe virtually any query we want, answering all sorts of questions:

https://github.com/michaelahlers/scala-examples/blob/209381b2085c69ffc951ee73d973647cd63519ce/src/main/scala-2/examples/basic/algebraicDataTypeEssentails/interfaceDesignImprovements/version2/InterfaceDesignImprovementsVersion2App.scala#L10-L34

Nothing extra, total flexibility, and it's drop-dead easy to use. Our consumers can tell us exactly what they want in perpetuity with an elegant and simple API.

Not so fast.

Could this enable consumers to craft expensive queries? Possibly. In the theme of a favorite trope of "preventing invalid states," we show here how additional types suggest how specific properties are indexed. Also, we've introduced the use of `NonEmptyList` from the widely popular [Cats][technology-typelevel-cats] library to enforce at minimum one predicate for our boolean operators.

It could at least make optimizing our most common use cases more difficult. We could always revisit making our request type an ADT and spelling out those optimized cases while adding another that accepts a `Predicate` as described here.

These are exercises for the reader.

[technology-typelevel-cats]: https://typelevel.org/cats/

## Conclusion

We've seen how Algebraic Data Types can help us design better interfaces. We've also suggested how they can help us implement those interfaces more easily. Of course, we've also seen how they can help us make confusing or invalid states unrepresentable.

Also, note that the Scala compiler will help catch mistakes at implementation. Should your cases change (whether by addition, update, or removal), it will be impossible—thanks to the `sealed` keyword attached to the ADT traits involved—to miss subsequent code changes to suit.

Your specific needs will be entirely different from this contrived example, but as you design your next API using Scala, consider how these techniques might help make its use self-documenting and bulletproof against unintentional use.
