package tutorial.monads.flightItinerary.common

object samples {

  val locator: Locator = Locator(
    toText = "A1B2C3",
  )

  val reservation: Reservation = Reservation(
    passenger = Passenger(
      name = "Grace Hopper",
    ),
    flight = Flight(
      number = "UAL123",
      origin = "PIT",
      destination = "DEN",
    ),
  )

  val ticket: Ticket = Ticket(
    number = 1234,
  )

  val seat: Seat = Seat(
    row = 14,
    column = 'C',
  )

}
