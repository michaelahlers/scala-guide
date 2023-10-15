# Frequently Asked Questions

## Why Scala 2.12?

Many teams still use Scala 2.12, and I don't want to neglect them. They need support from experienced Scala users. However, most material written on Scala now uses the latest dialect. That's great, of course! Scala 3 answers many (if not all) complaints about Scala 2. Ideally, everyone will migrate, but we must be realistic about those challenges in a business setting. To that end, I'll also provide equivalent examples in 2.13 and 3 where necessary or useful.

## Why sbt?

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
