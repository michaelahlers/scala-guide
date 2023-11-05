# Monads in Scala: Flight Itinerary

We have here a hypothetical (and, of course, contrived) system for working with flight reservations that we'll improve upon with monads.

## Problem Setup

Our setup features two key areas of emphasis:

- Models representing essential parts of our reservation (_e.g._, airline locator, reservation, seat).
- Services to fetch records associated with a reservation locator, specifically one to describe a confirmation.

The ultimate goal is to provide a passenger with a description of their confirmation—if applicable, given business rules.

Let's look at the details of our models first.

We have an airline record locator:

https://github.com/michaelahlers/scala-guide/blob/7f605687462d85df804b82308cb7258595df8efd/src/main/scala/tutorial/monads/flightItinerary/setup/Locator.scala#L3-L8

A reservation record, which who's traveling and on what flight:

https://github.com/michaelahlers/scala-guide/blob/7f605687462d85df804b82308cb7258595df8efd/src/main/scala/tutorial/monads/flightItinerary/setup/Reservation.scala#L3-L14

https://github.com/michaelahlers/scala-guide/blob/7f605687462d85df804b82308cb7258595df8efd/src/main/scala/tutorial/monads/flightItinerary/setup/Passenger.scala#L3-L10

https://github.com/michaelahlers/scala-guide/blob/7f605687462d85df804b82308cb7258595df8efd/src/main/scala/tutorial/monads/flightItinerary/setup/Flight.scala#L3-L16

And a confirmation record, which provides the reservation as mentioned earlier but also a ticket (which—we'll note—is [_not_ inherent in the purchase of a flight reservation][lifehacker-make-sure-your-flight-reservation-is-ticketed]) and assigned seat (which may set at any time between booking and departure):

[lifehacker-make-sure-your-flight-reservation-is-ticketed]: https://lifehacker.com/make-sure-your-flight-reservation-is-ticketed-before-yo-1836791737

https://github.com/michaelahlers/scala-guide/blob/71726ebe3aa746e14f7cd4ef9b56a964371a2644/src/main/scala/tutorial/monads/flightItinerary/setup/Confirmation.scala#L3-L12

https://github.com/michaelahlers/scala-guide/blob/7f605687462d85df804b82308cb7258595df8efd/src/main/scala/tutorial/monads/flightItinerary/setup/Ticket.scala#L3-L12

https://github.com/michaelahlers/scala-guide/blob/71726ebe3aa746e14f7cd4ef9b56a964371a2644/src/main/scala/tutorial/monads/flightItinerary/setup/Seat.scala#L3-L11

Before proceeding, let's summarize our business domain. A passenger makes an airline reservation on a desired flight (a specific performance between two airports) with their personal information (such as a name). At a point before departure, the reservation _may_ become ticketed and have a seat assigned.

> [!NOTE]  
> We've not made an optimal model for such a system—there are plenty of refinements we could make—but it is a sufficient illustration for our purposes.