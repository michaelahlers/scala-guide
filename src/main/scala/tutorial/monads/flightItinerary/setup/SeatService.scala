package tutorial.monads.flightItinerary.setup

case class SeatService(byLocator: Map[Locator, Seat]) {

  def getSeat(locator: Locator)(callback: Seat => Unit): Unit =
    callback(byLocator.get(locator).orNull)

}
