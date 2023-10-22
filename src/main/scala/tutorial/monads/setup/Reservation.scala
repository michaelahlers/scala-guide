package tutorial.monads.setup

case class Reservation(
  locator: String,
  person: Person,
  flight: Flight,
  ticket: Ticket,
  seat: Seat,
)
