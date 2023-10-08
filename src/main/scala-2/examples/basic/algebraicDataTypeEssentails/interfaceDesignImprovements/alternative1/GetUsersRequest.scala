package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.alternative1

sealed trait GetUsersRequest
object GetUsersRequest {

  case class ByPersonName(
    givenName: Argument[String],
    familyName: Argument[String],
  ) extends GetUsersRequest

  case class ByContactInformation(
    emailAddress: Argument[String],
    phoneNumber: Argument[String],
  ) extends GetUsersRequest

  case class ByLocale(
    city: Argument[String],
    region: Argument[String],
  ) extends GetUsersRequest

}
