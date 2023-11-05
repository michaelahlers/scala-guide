package tutorial.monads.flightItinerary.setup

case class SeatService(byLocator: Map[Locator, Seat]) {

  /**
   * Finds a [[Seat]] by its [[Locator]], and passes to the given callback function.
   *
   * @param callback Applied with an appropriate [[Seat]] or `null` if the [[Locator]] is not found or a [[Seat]] assigned for the [[Reservation]].
   */
  def getSeat(locator: Locator)(callback: Seat => Unit): Unit =
    callback(byLocator.get(locator).orNull)

}
