package tutorial.monads.flightItinerary.setup

case class TicketService(byLocator: Map[Reservation, Ticket]) {
  def getTicket[A](reservation: Reservation)(callback: Ticket => A): A =
    callback(byLocator.get(reservation).orNull)
}
