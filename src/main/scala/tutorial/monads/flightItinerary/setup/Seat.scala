package tutorial.monads.flightItinerary.setup

/**
 * Once assigned, describes where a [[Passenger]] is seated.
 *
 * @see [[Confirmation.seat]]
 */
case class Seat(
  row: Int,
  column: Char,
)
