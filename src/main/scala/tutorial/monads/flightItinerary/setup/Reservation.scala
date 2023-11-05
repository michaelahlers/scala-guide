package tutorial.monads.flightItinerary.setup

/**
 * ''Loosely'' based on an airline [[https://en.wikipedia.org/wiki/Passenger_name_record|passenger name record]] (or PNR). As with [[Locator]], not strictly conformant to the standard, but good enough for our purposes.
 *
 * @param passengers Required person information.
 * @param flight Required flight information.
 * @see [[https://en.wikipedia.org/wiki/Passenger_name_record]]
 */
case class Reservation(
  passengers: Seq[Passenger],
  flight: Flight,
)
