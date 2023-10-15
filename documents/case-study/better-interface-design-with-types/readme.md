# Case Study: Better Interface Design with Types

## Preamble

We can bring [[Algebraic Data Types|Glossary: Algebraic Data Types]] to bear on API design—they're not inherently limited to modeling business domains. Even if that seems obvious, I've found developers can miss out on their benefits: how they can precisely clarify your interface's intent and implementation behavior.

Here, we'll explore a hypothetical situation where we want to increase the capabilities of a service and help consumers better understand how to use it.

## Sources

- [`src/main/scala/caseStudy/betterInterfaceDesignWithTypes`](https://github.com/michaelahlers/scala-guide/tree/main/src/main/scala/caseStudy/betterInterfaceDesignWithTypes)
- [`src/test/scala/caseStudy/betterInterfaceDesignWithTypes`](https://github.com/michaelahlers/scala-guide/tree/main/src/test/scala/caseStudy/betterInterfaceDesignWithTypes)

## Topics

- [Algebraic Data Types][glossary-algebraic-data-types]
- [Covariance][scala-variances]
- [`sealed` types][scala-pattern-matching]
- [Extension Methods][glossary-extension-methods]

[glossary-algebraic-data-types]: ../../glossary/algebraic-data-types.md
[glossary-extension-methods]: ../../glossary/extension-methods.md
[scala-variances]: https://docs.scala-lang.org/tour/variances.html
[scala-pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html

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

## Exploring Solutions

How, exactly, do we achieve that—making invalid states impossible to represent? Let's walk through a naïve approach and iterate our way to a more optimal solution.

### Problem Setup

Let's consider our service definition:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/main/scala/caseStudy/betterInterfaceDesignWithTypes/setup/UserService.scala#L3-L5

That already searches by their given and family names:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/main/scala/caseStudy/betterInterfaceDesignWithTypes/setup/GetUsersRequest.scala#L3-L6

As explained, we quickly see deficiencies from the consumer's perspective:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/test/scala/caseStudy/betterInterfaceDesignWithTypes/setup/UserServiceSpec.scala#L9-L52

And if we need more properties, like a user's email address, phone number, city, and region? We've now conflated inexact identifiers (a user's name and phone number), a positive identifier (their email address), and demographic information (their locale) in the same query type.

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

As discussed previously, we (specifically, our service's consumers) must wonder about our query semantics. Documentation might address any confusion, but we can do much better with little work.

### First Version

Same service as before:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/main/scala/caseStudy/betterInterfaceDesignWithTypes/version1/UserService.scala#L3-L5

Before examining our revised request type, let's make a replacement for `Option` (and all the ambiguities it entails) by modeling how our arguments ought to match. Right out the gate, we find our types are themselves a form of documentation our fellow engineers can't miss:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/main/scala/caseStudy/betterInterfaceDesignWithTypes/version1/Expression.scala#L3-L15

Then, use them to make a `GetUsersRequest` that's expressive and intuitive:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/main/scala/caseStudy/betterInterfaceDesignWithTypes/version1/GetUsersRequest.scala#L3-L21

Which makes our use cases abundantly clear:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/test/scala/caseStudy/betterInterfaceDesignWithTypes/version1/UserServiceSpec.scala#L15-L73

We've already achieved some valuable improvements for ourselves and the service's consumers:

1. Query semantics are precise—the ambiguities caused by using `Option` are replaced with clear and concise per-property matching behavior.
1. Each query explicitly and unambiguously states what question it's answering, and expanding these cases is simple.
1. The implementation is outside the scope of this subject. Still, whatever it is, it's undoubtedly more straightforward, needing to handle a handful of cases instead of some permutation of the parameters.

However, there are drawbacks:

1. While we've reduced implementation work, our service might need more flexibility. (Or, it suits the requirements just fine! Understanding those specifically is the reader's task.)
1. It's incumbent upon consumers to call the service repeatedly to obtain either a union or intersection of the results. (We put merging records in their hands, which may be fine. Also, repeated calls could be cheap, or we can mitigate by additional cases, as described earlier.)

Here is a scenario where we deal more with tradeoffs than solutions. (Understanding and choosing between tradeoffs is an essential responsibility of our profession as software engineers.) But this pattern, overall, might be more than enough for most teams. It remains applicable for and inspires a narrow set of supported use cases that fit specific business or customer needs. (_I.e._, providing a generic query interface might be counterproductive to your requirements.)

While we could stop here, what if we wanted to get fancier and provide more flexibility? How would that look?

### Second Version

Let's state upfront: this approach is probably overkill. I'm giving it to inspire your imagination and expand your knowledge. In practice, we'd reach for a different tool (for example, [GraphQL][technology-graph-ql], as implemented by [Sangria][graph-ql-sangria] or [Caliban][graph-ql-zio-caliban]). That said, let's dive in and flex our Scala muscles a bit!

[technology-graph-ql]: https://graphql.org/
[graph-ql-sangria]: https://github.com/sangria-graphql/sangria
[graph-ql-zio-caliban]: https://zio.dev/ecosystem/community/caliban/

Once again, the same service:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/main/scala/caseStudy/betterInterfaceDesignWithTypes/version2/UserService.scala#L3-L5

And _almost_ the same expression (note the absence of a `Wildcard` case, which we don't need anymore):

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/main/scala/caseStudy/betterInterfaceDesignWithTypes/version2/Expression.scala#L3-L14

But now we introduce predicates to our request envelope, which may be composed with a lightweight `implicit` syntax:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/main/scala/caseStudy/betterInterfaceDesignWithTypes/version2/GetUsersRequest.scala#L6-L47

With our newfound expressive capabilities, we can describe any query we want:

https://github.com/michaelahlers/scala-guide/blob/501a563e4e7fe759752d200617dcfa630f233aad/src/test/scala/caseStudy/betterInterfaceDesignWithTypes/version2/UserServiceSpec.scala#L16-L72

Nothing extraneous, total flexibility, and it's drop-dead easy to use. Our consumers can describe whatever they might need with an elegant and simple API.

Let's think about the downsides.

Could this enable consumers to craft expensive queries? Possibly. But, as shown, there are ways to use types to prevent that. Also, we've introduced the use of `NonEmptyList` from the widely popular [Cats][technology-typelevel-cats] library to enforce at minimum one predicate.

It could at least make optimizing our most common use cases more difficult. We could go back to making the request type an ADT and spelling out those optimized cases while adding another that accepts a `Predicate` as described here.

These are exercises for the reader.

[technology-typelevel-cats]: https://typelevel.org/cats/

## Conclusion

We've seen how Algebraic Data Types can help us design better interfaces, make implementing those interfaces easier, and (most importantly) make confusing or invalid states unrepresentable.

Also, note that the Scala compiler will catch mistakes at implementation. Should your cases change (whether by addition, update, or removal), it will be impossible—thanks to the `sealed` keyword attached to the ADT traits involved—to miss subsequent code changes to suit.

Of course, your specific needs will be entirely different from this contrived example, but as you design your next API using Scala, consider how these techniques might help make its use self-documenting and bulletproof against unintentional use.

## See also

- [Case Study: Generic and Semantic Range Class][case-study-general-semantic-range-class]

[case-study-general-semantic-range-class]: ../general-semantic-range-class/readme.md
