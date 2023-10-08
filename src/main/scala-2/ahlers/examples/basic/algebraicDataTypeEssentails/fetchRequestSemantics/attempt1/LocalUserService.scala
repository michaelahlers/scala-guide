package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.attempt1

case class LocalUserService() extends UserService {
  import GetUsersRequest._

  override def getUsers(request: GetUsersRequest): GetUsersResponse =
    request match {

      case request: ByPersonName =>
        pprint.log(request)

        /** @todo */
        GetUsersResponse()

      case request: ByContactInformation =>
        pprint.log(request)

        /** @todo */
        GetUsersResponse()

      case request: ByLocale =>
        pprint.log(request)

        /** @todo */
        GetUsersResponse()

    }

}
