# Tutorial: Monads in Scala

## Rationale

**If you had to choose the most essential topic to understand for effective software engineering with Scala, it would be monads.** Whether you're a novice tinkering with Scala or a seasoned expert building world-class production systems, you use constructs that express this concept daily. Unsurprisingly, commentary about monads is abundant and diverse.[^1] Despite that, I've found a solid understanding of monads remains elusive for many developers. While monad tutorials are a dime a dozen, my experience as a mentor inspires this one.

[^1]: I've seen two common themes with monad tutorials: some attempt to explain the concept in terms of Haskell, others with a heaping pile of theory, and a mixture of the two. We'll do neither here.

## What problem am I solving?

Before finding a solution, let's first look at what we're trying to accomplish. Here, we want to compose simple things into more complex things. You might ask, "Isn't that practically _all_ of software development?" Indeed! (I said this is important.)

## Okay, I'll bite: what's a monad, and how does it help me?

**It's a box.**

Forget the [category theory][wikipedia-monad-category-theory].[^2] Forget the [laws][wikipedia-monad-functional-programming-laws]. Functors. Monoids. Set it aside _for the moment_. It's all useful knowledge, and you _should_ bring it into your practice as a programmer, but let's keep our hands dirty.

[^2]: But do enjoy a chuckle at [the formal definition][medium-felix-kuhl-monad-monoid-endofunctor].

[medium-felix-kuhl-monad-monoid-endofunctor]: https://medium.com/@felix.kuehl/a-monad-is-just-a-monoid-in-the-category-of-endofunctors-lets-actually-unravel-this-f5d4b7dbe5d6
[wikipedia-monad-category-theory]: https://en.wikipedia.org/wiki/Monad_(category_theory)
[wikipedia-monad-functional-programming-laws]]: https://en.wikipedia.org/wiki/Monad_(functional_programming)#Definition

To elaborate:

- A monad is a box.[^3]
- That box:
  - Contains _values_ (_i.e._, zero, one, or many).
  - Provides context for those values.
- Those values are accessed only by callbacks[^4].

[^3]: Credit to Stuart Saltzman ([GitHub][github-stuart-saltzman], [LinkedIn][linkedin-stuart-saltzman]) for this idea, which he's found effective in explaining to newcomers.
[^4]: This isn't _exactly_ correct, but it's sufficient for our purposes.

[github-stuart-saltzman]: https://github.com/stuartsaltzman
[linkedin-stuart-saltzman]: https://linkedin.com/in/stuartsaltzman/

## Monads in action!

### Problem Setup

Before showing monads, let's look at an example that would benefit from them. If you've explored this topic before, you might've encountered trivial use cases. These are helpful, of course, but may leave too much for the reader to figure out on their own.[^5] I'll present a hypothetical (and, of course, contrived) system for working with flight reservations that we'll improve upon with monads.

[^5]: In your author's opinion, the value of concepts like monads is less obvious in trivial examples. A concrete elaboration is helpful.

Our setup features two key areas of emphasis:

- Models representing essential parts of our reservation (_e.g._, airline locator, reservation, seat).
- Services to fetch records associated with a reservation locator, specifically one to describe a confirmation.

The ultimate goal is to provide a passenger with a description of their confirmation—if applicable, given business rules.

Let's look at the details of our models first.

We have an airline record locator:

https://github.com/michaelahlers/scala-guide/blob/0029a1f0414168f548d55b633a6347c757c04353/src/main/scala/tutorial/monads/flightItinerary/setup/Locator.scala#L3-L8

A reservation record, which who's traveling and on what flight:

https://github.com/michaelahlers/scala-guide/blob/9134977a8e3cf7b6fd49dd556e120da761b95b4f/src/main/scala/tutorial/monads/flightItinerary/setup/Reservation.scala#L3-L14

https://github.com/michaelahlers/scala-guide/blob/52b948a92ec8152b07121d933150f3d6a53303d4/src/main/scala/tutorial/monads/flightItinerary/setup/Passenger.scala#L3-L10

https://github.com/michaelahlers/scala-guide/blob/52b948a92ec8152b07121d933150f3d6a53303d4/src/main/scala/tutorial/monads/flightItinerary/setup/Flight.scala#L3-L16

A ticket model (which—we'll note—is [_not_ inherent in the purchase of a flight reservation][lifehacker-make-sure-your-flight-reservation-is-ticketed]):

[lifehacker-make-sure-your-flight-reservation-is-ticketed]: https://lifehacker.com/make-sure-your-flight-reservation-is-ticketed-before-yo-1836791737

https://github.com/michaelahlers/scala-guide/blob/47478072dafbfe03f8b7a3ef0fc841f7098ef656/src/main/scala/tutorial/monads/flightItinerary/setup/Ticket.scala#L3-L12

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
