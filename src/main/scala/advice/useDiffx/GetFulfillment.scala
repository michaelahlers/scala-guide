package advice.useDiffx

import advice.useDiffx.Payment.CreditCard
import com.softwaremill.quicklens._

case class GetFulfillment(fulfillments: Seq[Fulfillment]) {

  def byId(id: Fulfillment.Id): Option[Fulfillment] =
    fulfillments
      .find(_.id == id)
      .modify(_
        .each
        .payments
        .each
        .when[CreditCard]
        .number
        .checkDigit)
      .using(_ + 1)

}
