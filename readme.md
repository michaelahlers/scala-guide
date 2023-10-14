# Scala Guide

Helpful recommendations and practical solutions for the working professional.

## Introduction

I want to help developers better use Scala in their daily work. This repository aims to provide real-world, concrete examples that exercise Scala's key features and patterns without burying my audience in theory. These are borne of more than a decade of experience with many teams using the language and numerous tools typically associated.

## My Reader

You're a newcomer, a curious and continuing learner, or ~~an engineer who wants a better experience~~ (I'll be honest) you're frustrated and aren't having a good time. Especially in that last case, I'm here to help you and want you to give Scala a fair chance. You might be surprised at how easy it can make solving difficult problems.

You all have day jobs, want to get work done, and don't want to be browbeaten by academics. For those inclined, there's a world of material for you. I expect you to pick up a bit of theory here, but I'll work to keep it to the bare minimum necessary and explain it in terms even I can understand.

Scala veterans will find this less helpful, but I'm eager for their feedback.

And I hope everyone will submit issues or pull-requests!

## Case Studies

You'll find all content on [this project's wiki](../../wiki), but the following reference a few highlights.

### [Better Interface Design with Types][case-study-better-interface-design-with-types]

Inspired by a problem a former mentee of mine needed to solve, this illustrates how exercising Scala's type system features can simplify an API and clarify how to use it.

### Remember Your Numerics

Scala's `Numeric` interface is oft-forgotten. We all use it all the time (perhaps without knowing it). Still, we need to remember how it (being a quintessential type class) unlocks swaths of built-in functionality like arithmetic operations (including inequalities) and sorting. In these examples, I'll show how we should use this concept instead of concrete class methods that are harder to write and less complete.

_Coming soon_; see https://github.com/michaelahlers/scala-examples/issues/5.

### Semantic Range Representation

Scala provides a built-in `Range` type for representing a range of `Int`, but it's not generic. Here, I'll examine and propose improvements upon an implementation I've seen in practice.

_Coming soon_; see https://github.com/michaelahlers/scala-examples/issues/4.

### Use Tuples Judiciously for Branching

Quickly combining parameters into tuples and matching their arguments for branching is an appropriate use of Scala's features, but it doesn't scale. Overextending the arity can lead to confusing, buggy, and unmaintainable code. Luckily, Scala _wants_ you to introduce new data classes for this purpose eagerly.

_Coming soon_; see https://github.com/michaelahlers/scala-examples/issues/3.

[case-study-better-interface-design-with-types]: ../../wiki/Case-Study:-Better-Interface-Design-with-Types

## The Problem

Since I began using Scala professionally in 2012, it's become my favorite language. My views of this language and its associated tools are overwhelmingly positive—I've found it an elegant and powerful tool for solving all kinds of problems in various projects. Also, many professionals have shared their experiences about the language, and not all of it's as positive as mine.

There's been extensive discussion from industry and academic thought leaders as to why Scala struggles to gain traction and what its proponents can do about it.[^1] I won't repeat that here, but I'll offer my suggestions based on two recurring observations:

[^1]: [_Scala Resurrection_][john-de-goes-scala-resurrection] by [John de Goes][journal-john-de-goes]

[journal-john-de-goes]: https://degoes.net/
[john-de-goes-scala-resurrection]: https://degoes.net/articles/scala-resurrection

**First, many teams miss out on Scala's key features.**

And so they miss returns on their investment. It's another topic worth exploring (elsewhere), but it's sufficient to recognize that using Scala (and similar languages) comes with a cost. However, teams _can_ net significant dividends by using it well.

**Second, using the wrong programming paradigms.**

I've seen it happen enough that I feel compelled to address it. While Scala, by design, happily supports multiple paradigms (object-oriented and functional), no engineer will enjoy using it without thinking functional first and with too much reliance on, for example, mutable state, side effects, or breaking control flow (with exceptions, which are a frustratingly persistent kind of [go-to statement][wikipedia-considered-harmful]). These all have their utility (typically in optimization scenarios), but they're oil and water with a language like Scala and shouldn't be the first tools for which developers reach.

[wikipedia-considered-harmful]: https://en.wikipedia.org/wiki/Considered_harmful

## My Solution

I'm not competing with similar resources that expound on academic considerations. Those are worth the reader's attention; I'll refer to them as appropriate. _My_ reader might say, "Don't waste my time," so my examples will:

1. Ask, "What problem am I solving?"
1. Provide solutions that are easily adapted.
1. "Show my work" for the curious.

In addition to demonstrating the language, I'll extensively use the tools and libraries I've found most helpful in my experience. While that incurs strong opinions, most concepts naturally translate.

If I'm successful, my readers will find they can get more from the language and be more productive with it!

## Frequently Asked Questions

### Why Scala 2.12?

Many teams still use Scala 2.12, and I don't want to neglect them. They need support from experienced Scala users. However, most material written on Scala now uses the latest dialect. That's great, of course! Scala 3 answers many (if not all) complaints about Scala 2. Ideally, everyone will migrate, but we must be realistic about those challenges in a business setting. To that end, I'll also provide equivalent examples in 2.13 and 3 where necessary or useful.

### Why sbt?

That is, instead of legacy build systems or more recent upstarts.

[sbt][build-tool-sbt] (Simple Build Tool? Scala Build Tool?) has received a lot of criticism. While some of that criticism is valid, [Eugene Yokota][github-eugene-yokota] and his fellow contributors to the tool have done an outstanding job of understanding and addressing industry needs. As Scala 3 has answered complaints about Scala 2, modern versions of sbt have addressed its shortcomings from its early days.

For starters, sbt has moved away from "ASCII-art" syntaxes that were once trendy in the Scala community as it toyed with domain-specific languages aspiring to resemble mathematical notations. Its syntax has become plain and simple.

Also, it's helpful to understand that you don't have to cram the entire build definition into a single file. sbt is not a scripting language with linear, top-to-bottom execution. Builds can easily consist of small, understandable pieces that localize concerns (as this project demonstrates).

While it provides developers access to Scala for solving intricate problems, that's rarely (if ever!) the first tool developers should reach for when setting up their projects. The overwhelming majority of software projects are likewise overwhelmingly similar, and the need for bespoke solutions is vanishingly rare. I've repeatedly found there was an adequate "off-the-shelf" solution where developers chose to write code in their builds.

Lastly, some developers get distracted by the powerful scopes system sbt offers. But it's unnecessary to go beyond the basics in most cases; you can accomplish almost everything you need with a cursory understanding of configurations.

I respect (and celebrate!) challengers to any incumbent technology (for example, the excellent [mill][build-tool-mill] developed by [Li Haoyi][journal-li-haoyi]). Still, we need not discard sbt quite yet, and in pursuit of this project's goals, I'll be sticking with the tool most developers are likely to find in their day jobs.

[build-tool-mill]: https://github.com/com-lihaoyi/mill

[build-tool-sbt]: https://www.scala-sbt.org/

[github-eugene-yokota]: https://github.com/eed3si9n

[journal-li-haoyi]: https://www.lihaoyi.com/
