package tutorial.monads.flightItinerary.version1

/**
 * @param reservation Required [[Reservation]] information.
 * @param ticket ''Optional'' ticketing information; [[None]] if not yet ticketed.
 * @param seat ''Optional'' seat assignment; [[None]] is no seat has been assigned.
 */
case class Confirmation(
  reservation: Reservation,
  ticket: Option[Ticket],
  seat: Option[Seat],
)
