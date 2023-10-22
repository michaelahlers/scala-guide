package tutorial.monads.setup

object ExampleSpec {

  def reservation: Reservation = null

  if (null == reservation) null
  else {
    if (null == reservation.ticket || null == reservation.seat) null
    else {
      ???
    }
  }

}
