# Scala Examples

Practical solutions for the working professional.

## Introduction

I want to help developers better use Scala in their daily work. This repository aims to provide practical, concrete examples that exercise Scala's key features and patterns without burying my audience in theory. These are borne of more than a decade of experience using the language and numerous tools typically associated.

## My Reader

You're a newcomer, a curious and continuing learner, or an engineer who wants a better experience. Scala veterans will find this less helpful, but I'd encourage them to contribute to this guide.

## The Problem

Since I began using Scala professionally in 2012, it's become my favorite language. I've found it an elegant and effective tool for solving various problems. During that time, I've participated in a wide variety of projects and an even wider variety of perceptions about the language.

My views of this language and its associated tools are overwhelmingly positive. While I see tremendous benefits over its competitors (many of which have adopted Scala's features), this remains where I want to work the most. Unfortunately, I've found many engineers are frustrated with the language, and (if I'm honest) I'm not too surprised.

There's been extensive discussion from thought leaders from industry and academia as to why Scala struggles to gain hold. I won't repeat that here (I plan to reference this material later), but I offer my suggestions based on two recurring observations:

**First, many teams don't utilize Scala's key features to their potential to varying degrees.**

And so they miss returns on their investment in this technology. It's another topic I'll explore elsewhere, but using Scala (and similar languages) comes with a cost. However, with little effort, there are significant dividends over time. Each team will need to decide its comfort level, but there's often considerable value left on the table.

**Second, frequent attempts to mismatch programming paradigms with Scala best practices.**

I've seen it enough it's worth addressing. While Scala, by design, happily supports multiple paradigms (object-oriented and functional), no engineer will enjoy using it without thinking functional first, with too much reliance on, for example, mutable state, side effects, or breaking control flow (with exceptions, thinly veiled [go-to statements](https://en.wikipedia.org/wiki/Considered_harmful)). These all have utility (typically in optimization scenarios) but shouldn't be the first tools for which developers reach.

## My Solution

I'm not competing with similar resources that expound on academic considerations. Those are worth the reader's attention, and I'll refer to them as appropriate, but my reader might say, "Don't waste my time," so my examples will:

1. Ask, "What problem am I solving?"
1. Provide great solutions that are easily adapted.
1. "Show my work" for the interested reader.

In addition to demonstrating the language, I'll extensively use the tools and libraries I've found most helpful in my experience. While that incurs strong opinions, most concepts naturally translate.

If I'm successful, my readers will find ways to increase their productivity and find starting points to expand their knowledge.

# Frequently Asked Questions

## Why Scala 2.12?

Many teams still use Scala 2.12, and I want to pay attention to them. Most material written on Scala now uses the latest dialect, which makes sense: Scala 3 answers many (if not all) complaints about Scala 2. And ideally, everyone will migrate, but we must be realistic about those challenges in a business setting. I'll also provide equivalent examples in 2.13 and 3 where necessary or useful.
