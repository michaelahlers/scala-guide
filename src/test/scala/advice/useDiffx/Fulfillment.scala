package advice.useDiffx

import cats.data.NonEmptyList

case class Fulfillment(
  cart: Cart,
  payments: NonEmptyList[Payment],
)
