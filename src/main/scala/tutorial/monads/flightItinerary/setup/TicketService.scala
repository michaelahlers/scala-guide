package tutorial.monads.flightItinerary.setup

case class TicketService(byLocator: Map[Locator, Ticket]) {

  /**
   * Finds a [[Ticket]] by its [[Locator]], and passes to the given callback function.
   *
   * @param callback Applied with an appropriate [[Ticket]] or `null` if the [[Locator]] is not found or the [[Reservation]] has not been ''ticketed''.
   */
  def getTicket(locator: Locator)(callback: Ticket => Unit): Unit =
    callback(byLocator.get(locator).orNull)

}
