package betterInterfaceDesignWithTypes.version1

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
