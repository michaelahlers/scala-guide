package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.version2

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
