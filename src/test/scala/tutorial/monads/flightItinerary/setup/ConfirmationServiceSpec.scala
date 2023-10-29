package tutorial.monads.flightItinerary.setup

class ConfirmationServiceSpec {}

object ConfirmationServiceSpec {

  object samples {

    val locator: Locator = Locator(
      toText = "ABC123",
    )

    val reservation: Reservation = Reservation(
      passenger = Passenger(
        name = "Grace Hopper",
      ),
      flight = Flight(
        number = 123,
        origin = "PIT",
        destination = "DEN",
      ),
    )

    val ticket: Ticket = Ticket(
      number = 456,
    )

    val seat: Seat = Seat(
      row = 14,
      column = 'C',
    )

  }

}
