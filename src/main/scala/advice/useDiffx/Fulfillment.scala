package advice.useDiffx

import advice.useDiffx.Fulfillment.Id

case class Fulfillment(
  id: Id,
  cart: Cart,
  payments: Seq[Payment],
)

object Fulfillment {
  case class Id(toLong: Long) extends AnyVal
}
