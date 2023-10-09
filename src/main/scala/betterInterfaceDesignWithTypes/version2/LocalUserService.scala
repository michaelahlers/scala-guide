package betterInterfaceDesignWithTypes.version2

case class LocalUserService() extends UserService {

  override def getUsers(request: GetUsersRequest): GetUsersResponse = {
    pprint.log(request)

    /** @todo */
    GetUsersResponse()
  }

}
