package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.setup

case class GetUsersRequest(
  givenName: Option[String],
  familyName: Option[String],
)
