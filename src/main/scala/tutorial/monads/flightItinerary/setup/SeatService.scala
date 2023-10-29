package tutorial.monads.flightItinerary.setup

case class SeatService() {
  def getSeat[A](reservation: Reservation)(callback: Seat => A): A = ???
}
