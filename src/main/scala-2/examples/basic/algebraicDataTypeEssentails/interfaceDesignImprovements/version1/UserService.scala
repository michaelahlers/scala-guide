package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.version1

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
