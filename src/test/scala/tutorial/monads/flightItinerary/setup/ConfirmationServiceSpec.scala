package tutorial.monads.flightItinerary.setup

import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.FixtureAnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ConfirmationServiceSpec extends FixtureAnyFlatSpec with MockFactory {
  import ConfirmationServiceSpec.Fixtures
  import ConfirmationServiceSpec.samples

  "No reservation" should "return null" in { fixtures =>
    import fixtures._

    var description: String = null

    confirmationService
      .getConfirmation(samples.noReservation.locator) { confirmation =>
        description = ConfirmationService.describe(confirmation)
      }

    description.shouldBe(null)
  }

  "Not ticketed" should "return null" in { fixtures =>
    import fixtures._

    var description: String = null

    confirmationService.getConfirmation(samples.notTicketed.locator) { confirmation =>
      description = ConfirmationService.describe(confirmation)
    }

    description.shouldBe(null)
  }

  "Ticketed, not seated" should "return description" in { fixtures =>
    import fixtures._

    var description: String = null

    confirmationService.getConfirmation(samples.ticketedNotSeated.locator) { confirmation =>
      description = ConfirmationService.describe(confirmation)
    }

    description.shouldMatchTo("Passenger Grace Hopper is confirmed on flight from PIT to DEN has not been assigned a seat.")
  }

  "Ticketed, seated" should "return description" in { fixtures =>
    import fixtures._

    var description: String = null

    confirmationService.getConfirmation(samples.ticketedSeated.locator) { confirmation =>
      description = ConfirmationService.describe(confirmation)
    }

    description.shouldMatchTo("Passenger Grace Hopper is confirmed on flight from PIT to DEN has been assigned 14 C.")
  }

  override protected type FixtureParam = Fixtures
  override protected def withFixture(test: OneArgTest) = {
    import samples.notTicketed
    import samples.ticketedNotSeated
    import samples.ticketedSeated

    val reservationService = ReservationService(Map(
      notTicketed.locator -> notTicketed.reservation,
      ticketedNotSeated.locator -> ticketedNotSeated.reservation,
      ticketedSeated.locator -> ticketedSeated.reservation,
    ))

    val ticketService = TicketService(Map(
      ticketedNotSeated.locator -> ticketedNotSeated.ticket,
      ticketedSeated.locator -> ticketedSeated.ticket,
    ))

    val seatService = SeatService(Map(
      ticketedSeated.locator -> ticketedSeated.seat,
    ))

    val confirmationService = ConfirmationService(
      reservationService = reservationService,
      ticketService = ticketService,
      seatService = seatService,
    )

    test(Fixtures(
      confirmationService = confirmationService,
    ))
  }
}

object ConfirmationServiceSpec {

  case class Fixtures(
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
