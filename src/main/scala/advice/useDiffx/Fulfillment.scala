package advice.useDiffx

import advice.useDiffx.Fulfillment.Id
import cats.data.NonEmptyList

case class Fulfillment(
  id:Id,
  cart: Cart,
  payments: NonEmptyList[Payment],
)

object Fulfillment {
  case class Id(toLong: Long) extends AnyVal
}
