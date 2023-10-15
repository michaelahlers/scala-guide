# Case Study: Generic and Semantic Range Class

## Preamble

Before we proceed, consider using [the range class provided by Squants][github-typelevel-squants-quantity-range].[^1] But you might need to impose constraints on ranges not made easy by a third-party library.

[^1]: See also [Advice: Use Squants][advice-use-squants].

In any case, we have a teachable moment to present a quintessential application of [Algebraic Data Types][glossary-algebraic-data-types] to make our code easier to use and reason about.

[advice-use-squants]: ../advice/use-squants/readme.md
[github-typelevel-squants-quantity-range]: https://github.com/typelevel/squants/blob/650d4897a22b6aea214a885af06837b7d59e3d58/shared/src/main/scala/squants/QuantityRange.scala#L23

## Sources

- [`src/main/scala/caseStudy/genericSemanticRangeType`](https://github.com/michaelahlers/scala-guide/tree/main/src/main/scala/caseStudy/genericSemanticRangeType)
- [`src/test/scala/caseStudy/genericSemanticRangeType`](https://github.com/michaelahlers/scala-guide/tree/main/src/test/scala/caseStudy/genericSemanticRangeType)

## Topics

- [Algebraic Data Types][glossary-algebraic-data-types]
- [`sealed` types][scala-pattern-matching]
- [Extension Methods][glossary-extension-methods]

[glossary-algebraic-data-types]: ../../glossary/algebraic-data-types.md
[glossary-extension-methods]: ../../glossary/extension-methods.md
[scala-pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html

## What problem are we solving?

We need to represent a range of an arbitrary type to specify bounds that business logic will use to impose constraints. We can _imagine_ ways to coerce [Scala's built-in `Range` class][baeldung-scala-range] into serving our needs, but that might be awkward.

[baeldung-scala-range]: https://www.baeldung.com/scala/range

## Exploring Solutions

We probably already thought to author a `case class` to represent our range as a logical unit rather than pass around the left and right boundary values independently. `Option` seems a good choice to represent bounds, but far too often, `Option` (while extremely useful) is overloaded with unclear business concerns.[^2]

[^2]: This is made clear by [Case Study: Better Interface Design with Types][case-study-better-interface-design-with-types].

### Problem Setup

Our intuitive `Range` seems completely obvious:

https://github.com/michaelahlers/scala-guide/blob/d5c69c6674958f7e988897f3c659b09b73fb9073/src/main/scala/caseStudy/genericSemanticRangeClass/setup/Range.scala#L3-L6

As do it's typical uses:

https://github.com/michaelahlers/scala-guide/blob/d5c69c6674958f7e988897f3c659b09b73fb9073/src/test/scala/caseStudy/genericSemanticRangeClass/setup/RangeSpec.scala#L11-L31

But what if both `left` and `right` are `None`? We quickly see a problem:

https://github.com/michaelahlers/scala-guide/blob/6668df3e393bac435d310d42dc04c37b691e34c5/src/test/scala/caseStudy/genericSemanticRangeClass/setup/RangeSpec.scala#L33-L40

> [!NOTE]
> At this point, you might think, "Surely this is trivial," and I might agree.

### First Version

## See also

- [Case Study: Better Interface Design with Types][case-study-better-interface-design-with-types]

[case-study-better-interface-design-with-types]: ../better-interface-design-with-types/readme.md
