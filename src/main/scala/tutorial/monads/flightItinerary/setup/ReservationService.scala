package tutorial.monads.flightItinerary.setup

case class ReservationService(byLocator: Map[Locator, Reservation]) {
  def getReservation[A](locator: Locator)(callback: Reservation => A): A =
    callback(byLocator.get(locator).orNull)
}
