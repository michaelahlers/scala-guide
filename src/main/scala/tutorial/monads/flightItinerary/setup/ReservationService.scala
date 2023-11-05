package tutorial.monads.flightItinerary.setup

case class ReservationService(byLocator: Map[Locator, Reservation]) {

  def getReservation(locator: Locator)(callback: Reservation => Unit): Unit =
    callback(byLocator.get(locator).orNull)

}
