package tutorial.monads.flightItinerary.setup

/**
 * @param passenger Required person information.
 * @param flight Required flight information.
 * @param ticket Optional ticketing information; `null` if not yet ticketed.
 * @param seat Optional seat assignment; `null` is no seat has been assigned.
 */
case class Reservation(
  passenger: Passenger,
  flight: Flight,
  ticket: Ticket,
  seat: Seat,
)
