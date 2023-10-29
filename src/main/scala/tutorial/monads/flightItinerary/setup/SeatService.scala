package tutorial.monads.flightItinerary.setup

case class SeatService(byLocator: Map[Locator, Seat]) {
  def getSeat[A](locator: Locator)(callback: Seat => A): Unit = {
    callback(byLocator.get(locator).orNull)
    ()
  }
}
