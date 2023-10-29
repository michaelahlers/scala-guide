package tutorial.monads.flightItinerary.setup

case class TicketService(byLocator: Map[Locator, Ticket]) {
  def getTicket[A](locator: Locator)(callback: Ticket => A): Unit = {
    callback(byLocator.get(locator).orNull)
    ()
  }
}
