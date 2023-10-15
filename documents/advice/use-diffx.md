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

[illustration-hard-to-read]: https://github.com/michaelahlers/scala-guide/assets/2034877/9ff910c2-bee2-4b4d-ae07-f4ce5fceceaa
[illustration-easy-to-read]: https://github.com/michaelahlers/scala-guide/assets/2034877/e6b635a3-3ffd-4e15-8909-8105fc12467f

## Sources

- [`src/main/scala/advice/useDiffx`](https://github.com/michaelahlers/scala-guide/tree/dfbeaafccdd66799583f2f9fcca8adcaee93e254/src/main/scala/advice/useDiffx)
- [`src/test/scala/advice/useDiffx`](https://github.com/michaelahlers/scala-guide/tree/dfbeaafccdd66799583f2f9fcca8adcaee93e254/src/test/scala/advice/useDiffx)

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

```scala
package advice.useDiffx.diffx

import advice.useDiffx.Fulfillment
import com.softwaremill.diffx.Diff
import com.softwaremill.diffx.generic.auto._

object instances {

  /** You may wish to derive parts of the object graph should the need arise to compare isolated parts.*/
  implicit val diffProduct: Diff[Product] =
    Diff.summon[Product]

  /** Otherwise, you can benefit from automatic derivation apart from test code. */
  implicit val diffFulfillment: Diff[Fulfillment] =
    Diff.summon[Fulfillment]
}
```

In your tests, avoid derivation entirely; import your `Diff` instances and whatever matching syntax you prefer. For example:

```scala
package advice.useDiffx

import advice.useDiffx.diffx.instances._
import com.softwaremill.diffx._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher._

def expected: Fulfillment = ???
def actual: Fulfillment = ???

compare(actual, expected)
```

This approach provides a few benefits:

1. If a problem with compilation occurs, it will be isolated and easier to find (_i.e._, orthogonal to tests).
1. You've effectively cached the derivation of your `Diff` instances, permitting easy reuse.
1. Consumers outside your test can leverage them also, which justifies defining them with locality to your model definitions (as opposed to your tests).

[read-the-docs-diffx-usage-derivation]: https://diffx-scala.readthedocs.io/en/latest/usage/derivation.html
