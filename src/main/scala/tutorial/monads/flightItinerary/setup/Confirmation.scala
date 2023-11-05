package tutorial.monads.flightItinerary.setup

/**
 * @param reservation Required [[Reservation]] information.
 * @param ticket ''Optional'' ticketing information; `null` if not yet ticketed.
 * @param seat ''Optional'' seat assignment; `null` is no seat has been assigned.
 */
case class Confirmation(
  reservation: Reservation,
  ticket: Ticket,
  seat: Seat,
)
