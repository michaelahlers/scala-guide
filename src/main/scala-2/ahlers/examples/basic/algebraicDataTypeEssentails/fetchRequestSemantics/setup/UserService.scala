package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.setup

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
