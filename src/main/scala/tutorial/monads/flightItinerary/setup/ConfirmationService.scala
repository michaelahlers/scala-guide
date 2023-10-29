package tutorial.monads.flightItinerary.setup

case class ConfirmationService(
  reservationService: ReservationService,
  ticketService: TicketService,
  seatService: SeatService,
) {
  import reservationService.getReservation
  import seatService.getSeat
  import ticketService.getTicket

  /** A passenger wants to check on the ticketing and seating status of their reservation. */
  def getConfirmation[A](locator: Locator)(callback: Confirmation => A): A =
    getReservation(locator) { reservation =>
      getTicket(reservation) { ticket =>
        getSeat(reservation) { seat =>
          callback(Confirmation(
            reservation = reservation,
            ticket = ticket,
            seat = seat,
          ))
        }
      }
    }

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
          import confirmation.seat
          import reservation.{flight, passenger}

          s"Passenger ${passenger.name} is confirmed on " +
            s"flight from ${flight.origin} to ${flight.destination} " +
            (if (null == confirmation.seat) "has not been assigned a seat."
             else s"has been assigned ${seat.row} ${seat.column}.")
        }
      }
    }

}
