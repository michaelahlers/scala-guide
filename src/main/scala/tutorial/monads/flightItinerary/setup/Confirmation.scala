package tutorial.monads.flightItinerary.setup

import tutorial.monads.flightItinerary.common.Reservation
import tutorial.monads.flightItinerary.common.Seat
import tutorial.monads.flightItinerary.common.Ticket

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
