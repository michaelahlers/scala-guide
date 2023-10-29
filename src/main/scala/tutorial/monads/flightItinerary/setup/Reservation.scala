package tutorial.monads.flightItinerary.setup

/**
 * @param passenger Required person information.
 * @param flight Required flight information.
 */
case class Reservation(
  passenger: Passenger,
  flight: Flight
)
