package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.alternative2

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
