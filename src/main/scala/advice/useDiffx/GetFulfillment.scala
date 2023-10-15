package advice.useDiffx

case class GetFulfillment(fulfillments: Seq[Fulfillment]) {
  def byId(id: Fulfillment.Id): Option[Fulfillment] =
    fulfillments.find(_.id == id)
}
