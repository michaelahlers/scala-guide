package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.setup

case class LocalUserService() extends UserService {
  override def getUsers(request: GetUsersRequest): GetUsersResponse = {
    pprint.log(request)
    GetUsersResponse()
  }
}
