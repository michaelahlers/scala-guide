package tutorial.monads.setup

object ExampleSpec {

  /** Suppose a passenger wants to check on the ticketing and seating status of their reservation. */
  def reservation: Reservation = null

  if (null == reservation) null
  else {
    if (null == reservation.ticket || null == reservation.seat) null
    else {
      ???
    }
  }

}
