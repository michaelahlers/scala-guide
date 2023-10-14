package caseStudies.betterInterfaceDesignWithTypes.version1

sealed trait GetUsersRequest
object GetUsersRequest {

  case class ByPersonName(
    givenName: Expression[String],
    familyName: Expression[String],
  ) extends GetUsersRequest

  case class ByContactInformation(
    emailAddress: Expression[String],
    phoneNumber: Expression[String],
  ) extends GetUsersRequest

  case class ByLocale(
    city: Expression[String],
    region: Expression[String],
  ) extends GetUsersRequest

}
