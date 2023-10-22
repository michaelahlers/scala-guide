package tutorial.monads.setup

object StatusSpec {

  /** A passenger wants to check on the ticketing and seating status of their reservation. */
  def describe(reservation: Reservation): String =
    if (null == reservation) null
    else {
      if (null == reservation.passenger || null == reservation.flight) throw new IllegalArgumentException()

      if (null == reservation.ticket || null == reservation.seat) null
      else {
        import reservation.passenger
        import reservation.seat
        import reservation.flight

        s"Passenger ${passenger.name} " +
          s"has been assigned ${seat.row} ${seat.column} " +
          s"on flight ${flight.number} from ${flight.origin} to ${flight.destination}."
      }
    }

}
