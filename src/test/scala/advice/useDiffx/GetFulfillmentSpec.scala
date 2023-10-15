package advice.useDiffx

import advice.useDiffx.diffx.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.FixtureAnyWordSpec

class GetFulfillmentSpec extends FixtureAnyWordSpec {
  import GetFulfillmentSpec.Fixtures
  import GetFulfillmentSpec.samples

  "Assertion errors" can {

    "be hard to read" in { fixtures =>
      import fixtures.getFulfillment

      getFulfillment.byId(samples.fulfillment.id)
        .shouldEqual(Some(samples.fulfillment))
    }

    "be easy to read" in { fixtures =>
      import fixtures.getFulfillment

      getFulfillment.byId(samples.fulfillment.id)
        .shouldMatchTo(Some(samples.fulfillment))
    }

  }

  override protected type FixtureParam = Fixtures
  override protected def withFixture(test: OneArgTest) = {
    val getFulfillment = new GetFulfillment(
      fulfillments = Seq(samples.fulfillment),
    )

    test(Fixtures(
      getFulfillment = getFulfillment,
    ))
  }

}

object GetFulfillmentSpec {

  case class Fixtures(
    getFulfillment: GetFulfillment,
  )

  object samples {
    import Payment.Cash
    import Payment.CreditCard
    import Payment.CreditCard.Expiration
    import Payment.CreditCard.Number
    import Payment.CreditCard.Security
    import Product.Kind
    import Product.Make
    import Product.Model
    import Product.Year

    val fulfillment: Fulfillment = Fulfillment(
      id = Fulfillment.Id(123),
      cart = Cart(
        products = Seq(
          Product(
            kind = Kind("bicycle"),
            make = Make("Pinarello"),
            model = Model("F10"),
            year = Year(2019),
          ),
        ),
      ),
      payments = Seq(
        Cash,
        CreditCard(
          number = Number(
            issuerCode = 1234,
            accountNumber = 456789,
            checkDigit = 1,
          ),
          expiration = Expiration(
            month = 2,
            year = 2023,
          ),
          security = Security(
            code = 123,
          ),
        ),
      ),
    )
  }

}
