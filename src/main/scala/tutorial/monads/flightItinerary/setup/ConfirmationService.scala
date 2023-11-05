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
  def getConfirmation(locator: Locator)(callback: Confirmation => Unit): Unit =
    getReservation(locator) { reservation =>
      getTicket(locator) { ticket =>
        getSeat(locator) { seat =>
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

      if (null == reservation.passengers || null == reservation.flight) null
      else {
        if (null == confirmation.ticket) null
        else {
          import confirmation.seat
          import reservation.{flight, passengers}

          s"Passengers ${passengers.map(_.name).mkString(", ")} are confirmed on " +
            s"flight from ${flight.origin} to ${flight.destination} " +
            (if (null == confirmation.seat) "has not been assigned a seat."
             else s"has been assigned ${seat.row} ${seat.column}.")
        }
      }
    }

}
