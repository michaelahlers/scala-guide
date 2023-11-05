package tutorial.monads.flightItinerary.setup

case class ConfirmationService(
  reservationService: ReservationService,
  ticketService: TicketService,
  seatService: SeatService,
) {
  import reservationService.getReservation
  import seatService.getSeat
  import ticketService.getTicket

  /**
   * Obtains a [[Confirmation]] by its [[Locator]], and passes to the given callback function.
   *
   * @param callback Applied with an appropriate [[Confirmation]] or `null` if the [[Locator]] is not found. Parts of the [[Confirmation]] itself might be `null` if respective services can't find associated records.
   */
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

      if (null == reservation.passengers && reservation.passengers.nonEmpty || null == reservation.flight) null
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
