# Scala Examples

Practical solutions for the working professional.

## Introduction

I want to help developers better use Scala in their daily work. This repository aims to provide real-world, concrete examples that exercise Scala's key features and patterns without burying my audience in theory. These are borne of more than a decade of experience with many teams using the language and numerous tools typically associated.

## My Reader

You're a newcomer, a curious and continuing learner, or ~~an engineer who wants a better experience~~ (I'll be honest) you're frustrated and aren't having a good time. Especially in that last case, I'm here to help you and want you to give Scala a fair chance. You might be surprised at how easy it can make solving difficult problems.

You all have day jobs, want to get work done, and don't want to be browbeaten by academics. For those inclined, there's a world of material for you. I expect you to pick up a bit of theory here, but I'll work to keep it to the bare minimum necessary and explain it in terms even I can understand.

Scala veterans will find this less helpful, but I'd encourage them to contribute to this guide.

## The Problem

Since I began using Scala professionally in 2012, it's become my favorite language. I've found it an elegant and effective tool for solving all kinds of problems. During that time, I've participated in many projects and witnessed a huge variety of perceptions about the language.

My views of this language and its associated tools are overwhelmingly positive. While I see tremendous benefits over its competitors (many of which have adopted Scala's features), this remains where I want to work the most. Unfortunately, I've found many engineers are frustrated with the language, and (again, if I'm honest) I'm not too surprised.

There's been extensive discussion from thought leaders from industry and academia as to why Scala struggles to gain hold and what its proponents can do about it. (See also, [_Scala Resurrection_][john-de-goes-scala-resurrection] by [John de Goes][journal-john-de-goes].) I won't repeat that here (I plan to reference this material later), but I offer my suggestions based on two recurring observations:

[journal-john-de-goes]: https://degoes.net/
[john-de-goes-scala-resurrection]: https://degoes.net/articles/scala-resurrection

**First, many teams don't utilize Scala's key features to their potential to varying degrees.**

And so they miss returns on their investment in this technology. It's another topic I'll explore elsewhere, but using Scala (and similar languages) comes with a cost. However, with little effort, there are significant dividends over time. Each team will need to decide its comfort level, but there's often considerable value left on the table.

**Second, frequent attempts to mismatch programming paradigms with Scala best practices.**

I've seen it enough it's worth addressing. While Scala, by design, happily supports multiple paradigms (object-oriented and functional), no engineer will enjoy using it without thinking functional first, with too much reliance on, for example, mutable state, side effects, or breaking control flow (with exceptions, thinly veiled [go-to statements][wikipedia-considered-harmful]). These all have utility (typically in optimization scenarios) but shouldn't be the first tools for which developers reach.

[wikipedia-considered-harmful]: https://en.wikipedia.org/wiki/Considered_harmful

## My Solution

I'm not competing with similar resources that expound on academic considerations. Those are worth the reader's attention, and I'll refer to them as appropriate, but my reader might say, "Don't waste my time," so my examples will:

1. Ask, "What problem am I solving?"
1. Provide great solutions that are easily adapted.
1. "Show my work" for the interested reader.

In addition to demonstrating the language, I'll extensively use the tools and libraries I've found most helpful in my experience. While that incurs strong opinions, most concepts naturally translate.

If I'm successful, my readers will find ways to increase their productivity and find starting points to expand their knowledge.

## Frequently Asked Questions

### Why Scala 2.12?

Many teams still use Scala 2.12, and I want to pay attention to them. Most material written on Scala now uses the latest dialect, which makes sense: Scala 3 answers many (if not all) complaints about Scala 2. Ideally, everyone will migrate, but we must be realistic about those challenges in a business setting. I'll also provide equivalent examples in 2.13 and 3 where necessary or useful.

### Why sbt?

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

## What's with the weird package names?

If you're unfamiliar with it, the backtick denotes a literal identifier, allowing arbitrary text for symbol names. Generally, avoid using this language feature in your production code; these can be surprising and present caveats in some instances.

To illustrate:

```scala
val myValue: Int = 0
val `my-value`: Int = 1
```

There _are_ legitimate use cases for this feature, but those are few. I use them here to provide context for the example, avoid confusion caused by standard camel-case, and maintain a consistent directory structure. Hardly a universal invariant, I'd prefer code in this project mimic documentation than the other way around.
