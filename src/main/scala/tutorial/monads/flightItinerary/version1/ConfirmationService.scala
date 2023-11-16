package tutorial.monads.flightItinerary.version1

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import tutorial.monads.flightItinerary.common.Locator
import tutorial.monads.flightItinerary.common.Reservation
import tutorial.monads.flightItinerary.common.Seat
import tutorial.monads.flightItinerary.common.Ticket

case class ConfirmationService(
  reservationByLocator: Map[Locator, Reservation],
  ticketByLocator: Map[Locator, Ticket],
  seatByLocator: Map[Locator, Seat],
)(implicit
  executionContext: ExecutionContext,
) {

  /**
   * Finds a [[Reservation]] by its [[Locator]], and passes to the given callback function.
   *
   * @return [[Some]] matching [[Reservation]] or [[None]] if the [[Locator]] is not found.
   */
  def getReservation(locator: Locator): Future[Option[Reservation]] =
    Future(reservationByLocator.get(locator))

  /**
   * Finds a [[Ticket]] by its [[Locator]].
   *
   * @return [[Some]] matching [[Ticket]] or [[None]] if the [[Locator]] is not found or the [[Reservation]] has not been ''ticketed''.
   */
  def getTicket(locator: Locator): Future[Option[Ticket]] =
    Future(ticketByLocator.get(locator))

  /**
   * Finds a [[Seat]] by its [[Locator]].
   *
   * @return [[Some]] matching [[Seat]] or [[None]] if the [[Locator]] is not found or a [[Seat]] is not yet assigned for the [[Reservation]].
   */
  def getSeat(locator: Locator): Future[Option[Seat]] =
    Future(seatByLocator.get(locator))

  /**
   * Obtains a [[Confirmation]] by its [[Locator]].
   *
   * @return [[Some]] matching  [[Confirmation]] or [[None]] if the [[Locator]] is not found. Parts of the [[Confirmation]] itself might be [[None]] if respective services can't find associated records.
   */
  def getConfirmation(locator: Locator): Future[Option[Confirmation]] =
    for {
      reservationF <- getReservation(locator)
      ticket <- getTicket(locator)
      seat <- getSeat(locator)
    } yield for {
      reservation <- reservationF
    } yield Confirmation(
      reservation = reservation,
      ticket = ticket,
      seat = seat,
    )

}

object ConfirmationService {

  def describe(confirmation: Confirmation): Option[String] = {
    import confirmation.{reservation, ticket}

    ticket map { _ =>
      import confirmation.seat
      import reservation.{flight, passenger}

      s"Passenger ${passenger.name} is confirmed on " +
        s"flight from ${flight.origin} to ${flight.destination} " +
        seat.fold("has not been assigned a seat.") { seat =>
          s"is assigned ${seat.row} ${seat.column}."
        }
    }
  }

  def empty(implicit
    executionContext: ExecutionContext,
  ): ConfirmationService = ConfirmationService(
    reservationByLocator = Map.empty,
    ticketByLocator = Map.empty,
    seatByLocator = Map.empty,
  )

}
