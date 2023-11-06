package tutorial.monads.flightItinerary.version1

/**
 * Describe what flight is performing service for a particular market as part of a [[Reservation]].
 *
 * @param number Identifies a particular flight.
 * @param origin Departure airport code (''e.g'', `DEN`, `PIT`, `SFO`).
 * @param destination Arrival airport code.
 *
 * @see [[https://en.wikipedia.org/wiki/IATA_airport_code IATA airport code]]
 */
case class Flight(
  number: String,
  origin: String,
  destination: String,
)
