# Advice: Use Squants

## Summary

I've seen this enough times in my career it's worth mentioning: either out of ignorance of an off-the-shelf solution or well-meaning (but ultimately misguided) [Not Invented Here syndrome][wikipedia-not-invented-here], _avoid_ writing custom code for expressing and working with quantities, units (of measure), and dimensions.

The Scala ecosystem presents a uniquely powerful framework and library that addresses a dizzyingly wide variety of needs in the form of [Typelevel's Squants project][github-typelevel-squants].

As a library, Squants supports the representation and analysis of time, space, energy, _et cetera_, but more relevant to my readers is its support for [information and data rates][github-typelevel-squants-information] (_e.g._, kilobytes, bytes per second), [money, currencies, exchange rates, and prices][github-typelevel-squants-market] (_e.g._, $10, convert Dollars to Yen).

Bonus: it supports the Java Virtual Machine, [JavaScript][scala-js], and [native][scala-native] compilation targets.

[wikipedia-not-invented-here]: https://en.wikipedia.org/wiki/Not_invented_here
[github-typelevel-squants-information]: https://github.com/typelevel/squants/tree/650d4897a22b6aea214a885af06837b7d59e3d58/shared/src/main/scala/squants/information
[github-typelevel-squants-market]: https://github.com/typelevel/squants/tree/650d4897a22b6aea214a885af06837b7d59e3d58/shared/src/main/scala/squants/market
[scala-js]: https://www.scala-js.org/
[scala-native]: https://scala-native.org/

## Why not?

But you want to anyway, right? Sure, it's a fun project, but you're unlikely to write a similar library or framework as well as a dedicated team and community with considerable expertise and experience. And even if you did, now you've incurred a commensurate maintenance overhead. For most of us, our business or customer value is _not_ found in this domain.

We return to a question every software engineer should ask themselves all day long: "What problem am I solving?" Am I building a tool to work with monetary amounts? Or am I, say, pricing products correctly for my customers?

## It doesn't fit my needs!

Okay, fine! That's why I pointed out how [Squants][github-typelevel-squants] is both a library and a _framework_. If its library lacks the quantity, unit of measure, or dimension you specifically need, the framework is eminently extensible. Work within it to achieve your product goals. Nearly all its types are open to extension and inherently empower you with built-in arithmetic, sorting, and searching operators.

[github-typelevel-squants]: https://github.com/typelevel/squants
