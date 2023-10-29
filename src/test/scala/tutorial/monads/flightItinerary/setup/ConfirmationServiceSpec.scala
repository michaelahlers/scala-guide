package tutorial.monads.flightItinerary.setup

import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.FixtureAnyFlatSpec

class ConfirmationServiceSpec extends FixtureAnyFlatSpec with MockFactory {
  import ConfirmationServiceSpec.Fixtures

  override protected type FixtureParam = Fixtures
  override protected def withFixture(test: OneArgTest) = {
    import samples.noReservation

    val reservationService = ReservationService(Map(
    ))

    val ticketService = TicketService(Map(
    ))

    val seatService = SeatService(Map(
    ))

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

    object noReservation {
      val locator: Locator = Locator(
        toText = "no-reservation",
      )
    }

    object notTicketed {

      val locator: Locator = Locator(
        toText = "not-ticketed",
      )

      val reservation: Reservation = Reservation(
        passenger = Passenger(
          name = "Grace Hopper",
        ),
        flight = Flight(
          origin = "PIT",
          destination = "DEN",
        ),
      )

    }

    object ticketedNotSeated {

      val locator: Locator = Locator(
        toText = "ticketed-not-seated",
      )

      val reservation: Reservation = Reservation(
        passenger = Passenger(
          name = "Grace Hopper",
        ),
        flight = Flight(
          origin = "PIT",
          destination = "DEN",
        ),
      )

      val ticket: Ticket = Ticket(
        number = 12,
      )

    }

    object ticketedSeated {

      val locator: Locator = Locator(
        toText = "ticketed-seated",
      )

      val reservation: Reservation = Reservation(
        passenger = Passenger(
          name = "Grace Hopper",
        ),
        flight = Flight(
          origin = "PIT",
          destination = "DEN",
        ),
      )

      val ticket: Ticket = Ticket(
        number = 34,
      )

      val seat: Seat = Seat(
        row = 14,
        column = 'C',
      )

    }

  }

}
