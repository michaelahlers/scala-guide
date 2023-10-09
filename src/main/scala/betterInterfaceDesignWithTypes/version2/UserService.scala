package betterInterfaceDesignWithTypes.version2

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
