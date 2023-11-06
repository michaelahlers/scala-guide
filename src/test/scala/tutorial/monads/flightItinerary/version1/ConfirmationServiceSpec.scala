package tutorial.monads.flightItinerary.version1

import com.softwaremill.diffx.scalatest.DiffShouldMatcher._
import com.softwaremill.quicklens._
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers._
import scala.concurrent.Future

class ConfirmationServiceSpec extends AsyncFlatSpec {
  import ConfirmationServiceSpec.samples

  "No reservation" should "return null" in {
    import samples.locator

    val confirmationService: ConfirmationService = ConfirmationService.empty

    val description: Future[Option[String]] =
      for {
        confirmationF <- confirmationService.getConfirmation(locator)
      } yield for {
        confirmation <- confirmationF
        description <- ConfirmationService.describe(confirmation)
      } yield description

    description.map { description =>
      description.shouldBe(empty)
    }
  }

  "Not ticketed" should "return null" in {
    import samples.{locator, reservation}

    val confirmationService: ConfirmationService = ConfirmationService.empty
      .modify(_.reservationByLocator).using(_ + (locator -> reservation))

    val description: Future[Option[String]] =
      for {
        confirmationF <- confirmationService.getConfirmation(locator)
      } yield for {
        confirmation <- confirmationF
        description <- ConfirmationService.describe(confirmation)
      } yield description

    description.map { description =>
      description.shouldBe(empty)
    }
  }

  "Ticketed, not seated" should "return description" in {
    import samples.{locator, reservation, ticket}

    val confirmationService: ConfirmationService = ConfirmationService.empty
      .modify(_.reservationByLocator).using(_ + (locator -> reservation))
      .modify(_.ticketByLocator).using(_ + (locator -> ticket))

    val description: Future[Option[String]] =
      for {
        confirmationF <- confirmationService.getConfirmation(locator)
      } yield for {
        confirmation <- confirmationF
        description <- ConfirmationService.describe(confirmation)
      } yield description

    description.map { description =>
      description.shouldMatchTo(Some("Passenger Grace Hopper is confirmed on flight from PIT to DEN has not been assigned a seat."))
    }
  }

  "Ticketed, seated" should "return description" in {
    import samples.{locator, reservation, seat, ticket}

    val confirmationService: ConfirmationService = ConfirmationService.empty
      .modify(_.reservationByLocator).using(_ + (locator -> reservation))
      .modify(_.ticketByLocator).using(_ + (locator -> ticket))
      .modify(_.seatByLocator).using(_ + (locator -> seat))

    val description: Future[Option[String]] =
      for {
        confirmationF <- confirmationService.getConfirmation(locator)
      } yield for {
        confirmation <- confirmationF
        description <- ConfirmationService.describe(confirmation)
      } yield description

    description.map { description =>
      description.shouldMatchTo(Some("Passenger Grace Hopper is confirmed on flight from PIT to DEN is assigned 14 C."))
    }
  }

}

object ConfirmationServiceSpec {

  object samples {

    val locator: Locator = Locator(
      toText = "A1B2C3",
    )

    val reservation: Reservation = Reservation(
      passenger = Passenger(
        name = "Grace Hopper",
      ),
      flight = Flight(
        number = "UAL123",
        origin = "PIT",
        destination = "DEN",
      ),
    )

    val ticket: Ticket = Ticket(
      number = 1234,
    )

    val seat: Seat = Seat(
      row = 14,
      column = 'C',
    )

  }

}
