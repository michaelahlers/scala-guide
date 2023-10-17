# Scala Guide

Helpful recommendations and practical solutions for the working professional.

## Introduction

I want to help developers better use Scala in their daily work. This repository aims to provide real-world, concrete examples that exercise Scala's key features and patterns without burying my audience in theory. These are borne of more than a decade of experience with many teams using the language and numerous tools typically associated.

## Table of Contents

### Advice

#### [Use diffx][advice-use-diffx]

[diffx][advice-use-diffx] makes test errors _much_ easier to read, especially when comparing nontrivial object graphs.

[advice-use-diffx]: documents/advice/use-diffx/readme.md

#### [Use Squants][advice-use-squants]

[Squants][advice-use-squants] is an outstanding solution for software engineers who need to represent and analyze information and data rates; money, currencies, and respective conversions; and much more. Don't reinvent this wheel and rely on the wealth of features this project gives you for free.

[advice-use-squants]: documents/advice/use-squants/readme.md

### Case Studies

#### [Better Interface Design with Types][case-study-better-interface-design-with-types]

Inspired by a problem a former mentee of mine needed to solve, this illustrates how exercising Scala's type system features can simplify an API and clarify how to use it.

#### Remember Your Numerics

Scala's `Numeric` interface is oft-forgotten. We all use it all the time (perhaps without knowing it). Still, we need to remember how it (being a quintessential type class) unlocks swaths of built-in functionality like arithmetic operations (including inequalities) and sorting. In these examples, I'll show how we should use this concept instead of concrete class methods that are harder to write and less complete.

_Coming soon_; see https://github.com/michaelahlers/scala-examples/issues/5.

#### [Generic and Semantic Range Representation][case-study-generic-semantic-range-class]

Scala provides a built-in `Range` type for representing a range of `Int`, but it's not generic. Here, I'll examine and propose improvements upon an implementation I've seen in practice.

#### Use Tuples Judiciously for Branching

Quickly combining parameters into tuples and matching their arguments for branching is an appropriate use of Scala's features, but it doesn't scale. Overextending the arity can lead to confusing, buggy, and unmaintainable code. Luckily, Scala _wants_ you to introduce new data classes for this purpose eagerly.

_Coming soon_; see https://github.com/michaelahlers/scala-examples/issues/3.

[case-study-better-interface-design-with-types]: documents/case-study/better-interface-design-with-types/readme.md
[case-study-generic-semantic-range-class]: documents/case-study/general-semantic-range-class/readme.md

### [Frequently Asked Questions][frequently-asked-questions]

You might be wondering why Scala 2 is prevalent here, why I chose to lean on SBT for builds, or have another question about the guide. These questions and their answers are all found under [Frequently Asked Questions][frequently-asked-questions].

[frequently-asked-questions]: documents/frequently-asked-questions.md

## My Reader

You're a newcomer, a curious and continuing learner, or ~~an engineer who wants a better experience~~ (I'll be honest) you're frustrated and aren't having a good time. Especially in that last case, I'm here to help you and want you to give Scala a fair chance. You might be surprised at how easy it can make solving difficult problems.

You all have day jobs, want to get work done, and don't want to be overwhelmed with academics or expected to live on the bleeding edge. For those inclined, there's a world of material for you. I need you to pick up a bit of theory here, but I'll work to keep it to the bare minimum necessary (and explain in terms even _I_ can understand).

Scala veterans will find this less helpful, but I'm eager for their feedback.

And I hope everyone will contribute! See a problem or what to suggest a topic? Report an issue. Want to contribute yourself? Please submit a pull-request!

## The Problem

Since I began using Scala professionally in 2012, it's become my favorite language. My views of this language and its associated tools are overwhelmingly positive—I've found it an elegant and powerful tool for solving all kinds of problems in various projects. Also, many professionals have shared their experiences about the language, and not all of it's as positive as mine.

There's been extensive discussion from industry and academic thought leaders as to why Scala struggles to gain traction and what its proponents can do about it.[^1] I won't repeat that here, but I'll offer my suggestions based on two recurring observations:

[^1]: A well-covered topic in [_Scala Resurrection_][john-de-goes-scala-resurrection] by [John de Goes][journal-john-de-goes].

[journal-john-de-goes]: https://degoes.net/
[john-de-goes-scala-resurrection]: https://degoes.net/articles/scala-resurrection

**First, many teams miss out on Scala's key features.**

And so they miss returns on their investment. It's another topic worth exploring (elsewhere), but it's sufficient to recognize that using Scala (and similar languages) comes with a cost. However, teams _can_ net significant dividends by using it well.

**Second, using the wrong programming paradigms.**

I've seen it happen enough that I feel compelled to address it. While Scala, by design, happily supports multiple paradigms (object-oriented and functional), no engineer will enjoy using it without thinking functional first and with too much reliance on, for example, mutable state, side effects, or breaking control flow with exceptions[^2]. These all have their utility (typically in optimization scenarios), but they're oil and water with a language like Scala and shouldn't be the first tools for which developers reach.

[^2]: Exception throwing is, in reality, a (frustratingly persistent) kind of [go-to statement][wikipedia-considered-harmful]. We should avoid using this vestigial language feature as much as possible and [treat our errors as values][scala-book-functional-error-handling].

[wikipedia-considered-harmful]: https://en.wikipedia.org/wiki/Considered_harmful
[scala-book-functional-error-handling]: https://docs.scala-lang.org/scala3/book/fp-functional-error-handling.html

## My Solution

I'm not competing with similar resources that expound on academic considerations. Those are worth the reader's attention; I'll refer to them as appropriate. _My_ reader might say, "Don't waste my time," so my examples will:

1. Ask, "What problem am I solving?"
1. Provide solutions that are easily adapted.
1. "Show my work" through the process.

In addition to demonstrating the language, I'll extensively use the tools and libraries I've found most helpful in my experience. While that incurs strong opinions, most concepts naturally translate.

If I'm successful, my readers will find they can get more from the language and be more productive with it!
