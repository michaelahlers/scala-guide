package tutorial.monads.flightItinerary.setup

class ConfirmationService {

  def getReservation[A](locator: String)(callback: Reservation => A): A =
    ???

  def getTicket[A](reservation: Reservation)(callback: Ticket => A): A =
    ???

  def getSeat[A](reservation: Reservation)(callback: Seat => A): A =
    ???

  /** A passenger wants to check on the ticketing and seating status of their reservation. */
  def getConfirmation[A](locator: String)(callback: Confirmation => A): String =
    ???

}

object ConfirmationService {

  def describe(confirmation: Confirmation): String =
    if (null == confirmation.reservation) null
    else {
      import confirmation.reservation

      if (null == reservation.passenger || null == reservation.flight) null
      else {
        if (null == confirmation.ticket || null == confirmation.seat) null
        else {
          import reservation.passenger
          import reservation.flight
          import confirmation.seat

          s"Passenger ${passenger.name} is confirmed on" +
            s"flight ${flight.number} from ${flight.origin} to ${flight.destination} " +
            (if (null == confirmation.seat) "has not been assigned a seat."
             else s"has been assigned ${seat.row} ${seat.column}.")
        }
      }
    }

}
