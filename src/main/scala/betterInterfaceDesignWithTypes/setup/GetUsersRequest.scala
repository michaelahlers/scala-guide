package betterInterfaceDesignWithTypes.setup

case class GetUsersRequest(
  givenName: Option[String],
  familyName: Option[String],
)
