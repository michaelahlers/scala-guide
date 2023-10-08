package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.alternative1

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
