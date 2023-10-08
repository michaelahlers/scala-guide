package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.attempt1

case class LocalUserService() extends UserService {
  override def getUsers(request: GetUsersRequest): GetUsersResponse = {
    pprint.log(request)
    GetUsersResponse()
  }
}
