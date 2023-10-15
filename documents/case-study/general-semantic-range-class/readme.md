# Case Study: Generic and Semantic Range Class

## Preamble

Before we proceed, I **strongly** recommend using [the range class provided by Squants][github-typelevel-squants-quantity-range].[^1] I've seen _ad hoc_ implementations of such a class in `production` code that probably could've been avoided.

[^1]: See also [Advice: Use Squants][advice-use-squants].

That said, you might not have a choice, and we have a teachable moment to present a quintessential application of [Algebraic Data Types][glossary-algebraic-data-types] to make our code easier to use and reason about.

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
