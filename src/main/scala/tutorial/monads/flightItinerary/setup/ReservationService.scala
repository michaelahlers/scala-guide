package tutorial.monads.flightItinerary.setup

case class ReservationService(byLocator: Map[Locator, Reservation]) {

  /**
   * Finds a [[Reservation]] by its [[Locator]], and passes to the given callback function.
   *
   * @param callback Applied with an appropriate [[Reservation]] or `null` if the [[Locator]] is not found.
   */
  def getReservation(locator: Locator)(callback: Reservation => Unit): Unit =
    callback(byLocator.get(locator).orNull)

}
