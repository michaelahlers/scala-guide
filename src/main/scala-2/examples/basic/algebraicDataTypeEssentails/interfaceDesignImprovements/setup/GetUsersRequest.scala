package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.setup

case class GetUsersRequest(
  givenName: Option[String],
  familyName: Option[String],
)
