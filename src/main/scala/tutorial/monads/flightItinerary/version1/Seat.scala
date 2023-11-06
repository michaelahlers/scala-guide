package tutorial.monads.flightItinerary.version1

/**
 * Once assigned, describes where a [[Passenger]] is seated.
 *
 * @see [[Confirmation.seat]]
 */
case class Seat(
  row: Int,
  column: Char,
)
