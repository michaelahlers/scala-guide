package tutorial.monads.flightItinerary.setup

import org.scalamock.scalatest.MockFactory
import org.scalatest.funspec.FixtureAnyFunSpec

class ConfirmationServiceSpec extends FixtureAnyFunSpec with MockFactory {
  import ConfirmationServiceSpec.Fixtures

  override protected type FixtureParam = Fixtures

  override protected def withFixture(test: OneArgTest) = {
    val reservationService = mock[ReservationService]
    val ticketService = mock[TicketService]
    val seatService = mock[SeatService]

    val confirmationService = ConfirmationService(
      reservationService = reservationService,
      ticketService = ticketService,
      seatService = seatService,
    )

    test(Fixtures(
      reservationService = reservationService,
      ticketService = ticketService,
      seatService = seatService,
      confirmationService = confirmationService,
    ))
  }
}

object ConfirmationServiceSpec {

  case class Fixtures(
    reservationService: ReservationService,
    ticketService: TicketService,
    seatService: SeatService,
    confirmationService: ConfirmationService,
  )

  object samples {

    val locator: Locator = Locator(
      toText = "ABC123",
    )

    val reservation: Reservation = Reservation(
      passenger = Passenger(
        name = "Grace Hopper",
      ),
      flight = Flight(
        number = 123,
        origin = "PIT",
        destination = "DEN",
      ),
    )

    val ticket: Ticket = Ticket(
      number = 456,
    )

    val seat: Seat = Seat(
      row = 14,
      column = 'C',
    )

  }

}
