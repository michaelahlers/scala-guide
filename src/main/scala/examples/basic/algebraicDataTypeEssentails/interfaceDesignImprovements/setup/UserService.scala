package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.setup

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
