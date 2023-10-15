# Advice: Use diffx

## Summary

[SoftwareMill's diffx][github-softwaremill-diffx] presents a solution to a common problem with testing that will save you _hours_ trying to understand output when code breaks.

Writing tests can often be a challenge. Reading error messages when their assertions fail should never be. No matter what languages or tools you choose, any test presenting you with a wall of text wherein a subtle difference lies between the actual and expected result is a time thief. Until you know that, you can't even begin finding the bug (let alone start fixing it).

Rather than explain with a thousand words, I'll let a pair of screenshots do the talking.

## Illustrations

While more recent versions of ScalaTest try to help, it could be more obvious where the actual value differs from the expected. This shows how a built-in matcher makes it hard to spot the difference:

![hard-to-read assertion error][illustration-hard-to-read]

By contrast, diffx makes that difference blindingly obvious:

![easy-to-read assertion error][illustration-easy-to-read]

Hopefully, that's all it took to sell you on this valuable utility. It's redundant to document how to use diffx here—[the official documentation][read-the-docs-diffx] is outstanding—but I present an example to get you started and suggest a few caveats.

[github-softwaremill-diffx]: https://github.com/softwaremill/diffx
[read-the-docs-diffx]: https://diffx-scala.readthedocs.io/

[illustration-hard-to-read]: hard-to-read.png
[illustration-easy-to-read]: easy-to-read.png

## Sources

- [`src/main/scala/advice/useDiffx`](https://github.com/michaelahlers/scala-guide/tree/main/src/main/scala/advice/useDiffxx)
- [`src/test/scala/advice/useDiffx`](https://github.com/michaelahlers/scala-guide/tree/main/src/test/scala/advice/useDiffx)

## Caveats

### Automatic Derivation and Long Compile Times

Use [diffx's automatic derivation][read-the-docs-diffx-usage-derivation] _cautiously_.

There are two potential pitfalls:

1. Complex classes can dramatically slow down compilation.
1. Which is exasperated by repeated usages.

Wasted time accumulates fast if you're iterating on the compilation unit where the derivation occurs.

While mitigated with newer versions of both the library and Scala, developers should know this is a potential headache in advance.

A pattern I've found works well: following a packaging convention used by many popular Scala libraries, isolate your `Diff` type class instances away from frequently changing code. I've demonstrated this in the relevant sources for this advice, but let's spell it out here with context.

First, define your `Diff` instances in an appropriately namespaced `object`:

https://github.com/michaelahlers/scala-guide/blob/32f375248d1da911fd12e95902744d9a90a3a267/src/test/scala/advice/useDiffx/diffx/instances.scala#L7-L12

In your tests, avoid derivation entirely; import your `Diff` instances:

https://github.com/michaelahlers/scala-guide/blob/32f375248d1da911fd12e95902744d9a90a3a267/src/test/scala/advice/useDiffx/GetFulfillmentSpec.scala#L3-L4

And apply your preferred matching:

https://github.com/michaelahlers/scala-guide/blob/32f375248d1da911fd12e95902744d9a90a3a267/src/test/scala/advice/useDiffx/GetFulfillmentSpec.scala#L22-L28

This approach provides a few benefits:

1. If a problem with compilation occurs, it will be isolated and easier to find (_i.e._, orthogonal to tests).
1. You've effectively cached the derivation of your `Diff` instances, permitting easy reuse.
1. Consumers outside your test can leverage them also, which justifies defining them with locality to your model definitions (as opposed to your tests).

[read-the-docs-diffx-usage-derivation]: https://diffx-scala.readthedocs.io/en/latest/usage/derivation.html
