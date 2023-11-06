# Monads in Scala: Flight Itinerary

We have here a hypothetical (and, of course, contrived) system for working with flight reservations that we'll improve upon with monads.

## Problem Setup

Our setup features two key areas of emphasis:

- Models representing essential parts of our reservation (_e.g._, airline locator, reservation, seat).
- Services to fetch records associated with a reservation locator, specifically one to describe a confirmation.

The ultimate goal is to provide a passenger with a description of their confirmation—if applicable, given business rules.

Let's look at the details of our models first.

We have an airline record locator:

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/Locator.scala#L3-L8

A reservation record, which who's traveling and on what flight:

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/Reservation.scala#L3-L14

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/Passenger.scala#L3-L10

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/Flight.scala#L3-L16

And a confirmation record, which provides the reservation as mentioned earlier but also a ticket (which—we'll note—is [_not_ inherent in the purchase of a flight reservation][lifehacker-make-sure-your-flight-reservation-is-ticketed]) and assigned seat (which may set at any time between booking and departure):

[lifehacker-make-sure-your-flight-reservation-is-ticketed]: https://lifehacker.com/make-sure-your-flight-reservation-is-ticketed-before-yo-1836791737

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/Confirmation.scala#L3-L12

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/Ticket.scala#L3-L12

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/Seat.scala#L3-L11

Before we look at business logic, let's summarize our business domain. A passenger makes an airline reservation on a desired flight (a specific performance between two airports) with their personal information (such as a name). At a point before departure, the reservation _may_ become ticketed and have a seat assigned.

> [!NOTE]  
> We've not made an optimal model for such a system—there are plenty of refinements we could make—but it is a sufficient illustration for our purposes.

Now that we understand our models, let's explore what we can do with them and how we get their values!

As explained earlier, we want to describe our `Confirmation` in plain language for our passengers to understand. For example:

```
Passenger Grace Hopper is confirmed on flight from PIT to DEN and is assigned 14 C.
```

Our formatting function is:

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/ConfirmationService.scala#L55-L73

Look at all that `null` checking. While there _are_ better techniques in most modern languages[^null-safety], this serves as a stark contrast to the simplicity and elegance we'll demonstrate shortly.

[^null-safety]: Several languages have introduced their flavors of null safety, for example, [Kotlin has its `?` operator][kotlin-null-safety], [Java introduced `Optional`][baeldung-java-avoid-null-checking], and so on. While useful, these (as this guide aims to demonstrate) are different from (and arguably inferior to) the expressiveness and soundness offered by monads in languages like Scala or [Rust][rust-std-option].

[kotlin-null-safety]: https://kotlinlang.org/docs/null-safety.html
[baeldung-java-avoid-null-checking]: https://www.baeldung.com/java-avoid-null-check
[rust-std-option]: https://doc.rust-lang.org/std/option/

Now, our task is to aggregate these constituent parts of a reservation from various dedicated (asynchronous, in practice) services so we can apply that `describe` function and get our result. Our implementation starts to look even worse:

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/main/scala/tutorial/monads/flightItinerary/setup/ConfirmationService.scala#L33-L49

Now we've ventured into deeply nested (and difficult to maintain) callbacks[^callback-mitigation], missed an opportunity for parallelism (notice that the `Ticket` and `Seat` could've been gotten simultaneously), and our usage of this service necessitates side effects:

[^callback-mitigation]: In a similar vein as with the aforementioned `null`-checking, there are better techniques. For example, [JavaScript's `Promise` can be flattened into chains][mozilla-javascript-using-promises]. And, as before, these fundamentally differ from monads in form and function, as we'll see.

https://github.com/michaelahlers/scala-guide/blob/67110dea2a5e5bb552aaa013c422ce500b185137/src/test/scala/tutorial/monads/flightItinerary/setup/ConfirmationServiceSpec.scala#L65-L71

[mozilla-javascript-using-promises]: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Using_promises

> [!NOTE]  
> Our `ConfirmationService` could (and _should_), in practice, be further [decomposed into smaller units of functionality][wikipedia-functional-decomposition], but that'd needlessly clutter this example.

[wikipedia-functional-decomposition]: https://en.wikipedia.org/wiki/Functional_decomposition
