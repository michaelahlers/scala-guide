package tutorial.monads.flightItinerary.setup

import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import com.softwaremill.quicklens._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ConfirmationServiceSpec extends AnyFlatSpec {

  "No reservation" should "return null" in {
    val locator: Locator = Locator(
      toText = "no-reservation",
    )

    val confirmationService: ConfirmationService = ConfirmationService.empty

    var description: String = null

    confirmationService
      .getConfirmation(locator) { confirmation =>
        description = ConfirmationService.describe(confirmation)
      }

    description.shouldBe(null)
  }

  "Not ticketed" should "return null" in {
    val locator: Locator = Locator(
      toText = "not-ticketed",
    )

    val reservation: Reservation = Reservation(
      passenger = Passenger(
        name = "Grace Hopper",
      ),
      flight = Flight(
        number = 12,
        origin = "PIT",
        destination = "DEN",
      ),
    )

    val confirmationService: ConfirmationService = ConfirmationService.empty
      .modify(_.reservationByLocator).using(_ + (locator -> reservation))

    var description: String = null

    confirmationService.getConfirmation(locator) { confirmation =>
      description = ConfirmationService.describe(confirmation)
    }

    description.shouldBe(null)
  }

  "Ticketed, not seated" should "return description" in {
    val locator: Locator = Locator(
      toText = "ticketed-not-seated",
    )

    val reservation: Reservation = Reservation(
      passenger = Passenger(
        name = "Grace Hopper",
      ),
      flight = Flight(
        number = 23,
        origin = "PIT",
        destination = "DEN",
      ),
    )

    val ticket: Ticket = Ticket(
      number = 23,
    )

    val confirmationService: ConfirmationService = ConfirmationService.empty
      .modify(_.reservationByLocator).using(_ + (locator -> reservation))
      .modify(_.ticketByLocator).using(_ + (locator -> ticket))

    var description: String = null

    confirmationService.getConfirmation(locator) { confirmation =>
      description = ConfirmationService.describe(confirmation)
    }

    description.shouldMatchTo("Passenger Grace Hopper is confirmed on flight from PIT to DEN has not been assigned a seat.")
  }

  "Ticketed, seated" should "return description" in {
    val locator: Locator = Locator(
      toText = "ticketed-seated",
    )

    val reservation: Reservation = Reservation(
      passenger = Passenger(
        name = "Grace Hopper",
      ),
      flight = Flight(
        number = 34,
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

    val confirmationService: ConfirmationService = ConfirmationService.empty
      .modify(_.reservationByLocator).using(_ + (locator -> reservation))
      .modify(_.ticketByLocator).using(_ + (locator -> ticket))
      .modify(_.seatByLocator).using(_ + (locator -> seat))

    var description: String = null

    confirmationService.getConfirmation(locator) { confirmation =>
      description = ConfirmationService.describe(confirmation)
    }

    description.shouldMatchTo("Passenger Grace Hopper is confirmed on flight from PIT to DEN has been assigned 14 C.")
  }

}

object ConfirmationServiceSpec {}
