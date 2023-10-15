package advice.useDiffx

sealed trait Payment
object Payment {
  import CreditCard.{Expiration, Number, Security}

  case object Cash extends Payment
  case class CreditCard(
    number: Number,
    expiration: Expiration,
    security: Security,
  ) extends Payment
  object CreditCard {

    case class Number(
                       issuerCode: Int,
                       accountNumber: Int,
                       checkDigit: Int,
    )

    case class Expiration(
      month: Int,
      year: Int,
    )

    case class Security(
      code: String,
    )

  }

}
