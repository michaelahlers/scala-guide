package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.attempt1

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
