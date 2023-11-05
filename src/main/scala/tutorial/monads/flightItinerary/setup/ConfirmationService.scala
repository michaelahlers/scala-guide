package tutorial.monads.flightItinerary.setup

case class ConfirmationService(
  reservationByLocator: Map[Locator, Reservation],
  ticketByLocator: Map[Locator, Ticket],
  seatByLocator: Map[Locator, Seat],
) {

  /**
   * Finds a [[Reservation]] by its [[Locator]], and passes to the given callback function.
   *
   * @param callback Applied with an appropriate [[Reservation]] or `null` if the [[Locator]] is not found.
   */
  def getReservation(locator: Locator)(callback: Reservation => Unit): Unit =
    callback(reservationByLocator.get(locator).orNull)

  /**
   * Finds a [[Ticket]] by its [[Locator]], and passes to the given callback function.
   *
   * @param callback Applied with an appropriate [[Ticket]] or `null` if the [[Locator]] is not found or the [[Reservation]] has not been ''ticketed''.
   */
  def getTicket(locator: Locator)(callback: Ticket => Unit): Unit =
    callback(ticketByLocator.get(locator).orNull)

  /**
   * Finds a [[Seat]] by its [[Locator]], and passes to the given callback function.
   *
   * @param callback Applied with an appropriate [[Seat]] or `null` if the [[Locator]] is not found or a [[Seat]] assigned for the [[Reservation]].
   */
  def getSeat(locator: Locator)(callback: Seat => Unit): Unit =
    callback(seatByLocator.get(locator).orNull)

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

      if (null == reservation.passenger || null == reservation.flight) null
      else {
        if (null == confirmation.ticket) null
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

  val empty: ConfirmationService = ConfirmationService(
    reservationByLocator = Map.empty,
    ticketByLocator = Map.empty,
    seatByLocator = Map.empty,
  )

}
