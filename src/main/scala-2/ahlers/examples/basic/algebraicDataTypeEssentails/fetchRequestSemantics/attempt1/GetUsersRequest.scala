package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.attempt1

sealed trait GetUsersRequest
object GetUsersRequest {

  case class ByPersonName(
    givenName: Option[String],
    familyName: Option[String],
  ) extends GetUsersRequest

  case class ByContactInformation(
    emailAddress: Option[String],
    phoneNumber: Option[String],
  ) extends GetUsersRequest

  case class ByLocale(
    city: Option[String],
    region: Option[String],
  ) extends GetUsersRequest

}
