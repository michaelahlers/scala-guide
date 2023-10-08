package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.attempt1

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
