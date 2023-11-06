package tutorial.monads.flightItinerary.version1

/**
 * A confirmed ticket for a [[Reservation]]. A possibly little-known fact is these are not always assigned at the time a flight is booked.
 *
 * ''That'' must be taken into consideration for our purposes here: a [[Reservation]] may exist without a confirmed [[Ticket]].
 *
 * @see [[Confirmation.ticket]]
 *
 * @see [[https://lifehacker.com/make-sure-your-flight-reservation-is-ticketed-before-yo-1836791737 Make Sure Your Flight Reservation Is Ticketed Before You Travel]]
 */
case class Ticket(
  number: Int,
)
