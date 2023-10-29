package tutorial.monads.flightItinerary.setup

case class TicketService () {
  def getTicket[A](reservation: Reservation)(callback: Ticket => A): A = ???
}
