package tutorial.monads.flightItinerary.setup

case class ReservationService() {
  def getReservation[A](locator: Locator)(callback: Reservation => A) = ???
}
