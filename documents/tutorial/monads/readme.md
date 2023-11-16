# Tutorial: Monads in Scala

## Rationale

**If you had to choose the most essential topic to understand for effective software engineering with Scala, it would be monads.** Whether you're a novice tinkering with Scala or a seasoned expert building world-class production systems, you use constructs that express this concept daily. Unsurprisingly, commentary about monads is abundant and diverse.[^monad-tutorials] Despite that, I've found a solid understanding of monads remains elusive for many developers. While monad tutorials are a dime a dozen, my experience as a mentor inspires this one.

[^monad-tutorials]: I've seen two common themes with monad tutorials: some attempt to explain the concept in terms of Haskell, others with a heaping pile of theory, and a mixture of the two. We'll do neither here.

## What problem am I solving?

Before finding a solution, let's first look at what we're trying to accomplish. Here, we want to compose simple things into more complex things. You might ask, "Isn't that practically _all_ of software development?" Indeed! (I said this is important.)

## Okay, I'll bite: what's a monad, and how does it help me?

**It's a box.**

Forget the [category theory][wikipedia-monad-category-theory].[^category-theory-endofunctor-joke] Forget the [laws][wikipedia-monad-functional-programming-laws]. Functors. Monoids. Set it aside _for the moment_. It's all useful knowledge, and you _should_ bring it into your practice as a programmer, but let's keep our hands dirty.

[^category-theory-endofunctor-joke]: But do enjoy a chuckle at [the formal definition][medium-felix-kuhl-monad-monoid-endofunctor].

[medium-felix-kuhl-monad-monoid-endofunctor]: https://medium.com/@felix.kuehl/a-monad-is-just-a-monoid-in-the-category-of-endofunctors-lets-actually-unravel-this-f5d4b7dbe5d6
[wikipedia-monad-category-theory]: https://en.wikipedia.org/wiki/Monad_(category_theory)
[wikipedia-monad-functional-programming-laws]: https://en.wikipedia.org/wiki/Monad_(functional_programming)#Definition

To elaborate:

- A monad is a box.[^box-credit-stuart-saltzman]
- That box:
  - Contains _values_ (_i.e._, zero, one, or many).
  - Provides context for those values.
- Those values are accessed only by callbacks[^monad-callback-metaphor].

[^box-credit-stuart-saltzman]: Credit to Stuart Saltzman ([GitHub][github-stuart-saltzman], [LinkedIn][linkedin-stuart-saltzman]) for this idea, which he's found effective in explaining to newcomers.
[^monad-callback-metaphor]: This isn't _exactly_ correct, but it's sufficient for our purposes.

[github-stuart-saltzman]: https://github.com/stuartsaltzman
[linkedin-stuart-saltzman]: https://linkedin.com/in/stuartsaltzman/

## Monads in action!

We'll demonstrate monads with examples that would benefit from them. If you've explored this topic before, you might've encountered trivial use cases. These are helpful, of course, but may leave too much for the reader to figure out on their own.[^trivial-examples-less-helpful]

[^trivial-examples-less-helpful]: In your author's opinion, the value of concepts like monads is less obvious in trivial examples. A concrete elaboration is helpful.

### Flight Itinerary

[A detailed example that models a few rudimentary parts of an airline reservation system][monads-in-scala-flight-itinerary] and improves upon it by replacing `null` values and callbacks with `Option` and `Future`, respectively.

[monads-in-scala-flight-itinerary]: flightItinerary/readme.md

## What's next?

One of the most promising (and exciting) directions Scala's headed is towards [functional effect systems][medium-wix-engineering-demystifying-functional-effect-systems] (e.g., [ZIO][zio], [Cats Effect][typelevel-cats-effect]). While many teams have isolated islands of functional programming in their projects, these systems fully embrace what we've discussed here as the central abstraction to build whole programs with enormous productivity benefits. If you're comfortable with what we've discussed, leveling up your Scala technology stack will be _much_ easier.

[zio]: https://zio.dev
[typelevel-cats-effect]: https://typelevel.org/cats-effect/
[medium-wix-engineering-demystifying-functional-effect-systems]: https://medium.com/wix-engineering/demystifying-functional-effect-systems-in-scala-14419039a423

## Further Reading

This guide was probably not your first exposure to monads in Scala. It most certainly should _not_ be your last. With the basics well in hand, I strongly advise everyone who uses Scala to pursue this topic in-depth, and these resources will help:

- [_A Monads Approach for Beginners, in Scala_ by Daniel Ciocîrlan for Rock the JVM][rockthejvm-monads]
- [_Monads in Scala_ by Riccardo Cardin for Baeldung][baeldung-scala-monads]
- [_A Monad is just a Monoid…_ by Michele Stieven][medium-michele-stieven-monad-is-monoid]
- [_Monads and why do they matter_ by Yurii Gorbylov][medium-yurii-gorbylov-moands-and-why-they-matter]

[rockthejvm-monads]: https://blog.rockthejvm.com/monads/
[baeldung-scala-monads]: https://www.baeldung.com/scala/monads
[medium-michele-stieven-monad-is-monoid]: https://michelestieven.medium.com/a-monad-is-just-a-monoid-a02bd2524f66
[medium-yurii-gorbylov-moands-and-why-they-matter]: https://medium.com/@yuriigorbylov/monads-and-why-do-they-matter-9a285862e8b4
