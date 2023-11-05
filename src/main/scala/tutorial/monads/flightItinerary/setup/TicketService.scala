package tutorial.monads.flightItinerary.setup

case class TicketService(byLocator: Map[Locator, Ticket]) {

  def getTicket(locator: Locator)(callback: Ticket => Unit): Unit =
    callback(byLocator.get(locator).orNull)

}
