package tutorial.monads.setup

/**
 * @param passenger Required person information.
 * @param flight Required flight information.
 * @param ticket Optional ticketing information.
 * @param seat Optional seat assignment.
 */
case class Reservation(
  passenger: Passenger,
  flight: Flight,
  ticket: Ticket,
  seat: Seat,
)
