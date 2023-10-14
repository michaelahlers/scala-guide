package caseStudies.betterInterfaceDesignWithTypes.setup

trait UserService {
  def getUsers(request: GetUsersRequest): GetUsersResponse
}
