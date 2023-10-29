package tutorial.monads.flightItinerary.setup

case class SeatService(byLocator: Map[Reservation, Seat]) {
  def getSeat[A](reservation: Reservation)(callback: Seat => A): A =
    callback(byLocator.get(reservation).orNull)
}
