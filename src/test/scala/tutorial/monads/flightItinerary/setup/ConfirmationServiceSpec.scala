package tutorial.monads.flightItinerary.setup

import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ConfirmationServiceSpec extends AnyFlatSpec {

  "No reservation" should "return null" in {
    val locator: Locator = Locator(
      toText = "no-reservation",
    )

    val confirmationService = ConfirmationService(
      reservationByLocator = Map.empty,
      ticketByLocator = Map.empty,
      seatByLocator = Map.empty,
    )

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

    val confirmationService = ConfirmationService(
      reservationByLocator = Map(locator -> reservation),
      ticketByLocator = Map.empty,
      seatByLocator = Map.empty,
    )

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

    val confirmationService = ConfirmationService(
      reservationByLocator = Map(locator -> reservation),
      ticketByLocator = Map(locator -> ticket),
      seatByLocator = Map.empty,
    )

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

    val confirmationService = ConfirmationService(
      reservationByLocator = Map(locator -> reservation),
      ticketByLocator = Map(locator -> ticket),
      seatByLocator = Map(locator -> seat),
    )

    var description: String = null

    confirmationService.getConfirmation(locator) { confirmation =>
      description = ConfirmationService.describe(confirmation)
    }

    description.shouldMatchTo("Passenger Grace Hopper is confirmed on flight from PIT to DEN has been assigned 14 C.")
  }

}
