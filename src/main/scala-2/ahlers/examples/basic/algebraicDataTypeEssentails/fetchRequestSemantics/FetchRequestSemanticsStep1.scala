package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics

object FetchRequestSemanticsStep1 {

  case class RequestUsers(
    givenName: Option[String],
    familyName: Option[String],
  )

}
