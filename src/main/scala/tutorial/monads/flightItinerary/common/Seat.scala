package tutorial.monads.flightItinerary.common

/**
 * Once assigned, describes where a [[Passenger]] is seated.
 */
case class Seat(
  row: Int,
  column: Char,
)
