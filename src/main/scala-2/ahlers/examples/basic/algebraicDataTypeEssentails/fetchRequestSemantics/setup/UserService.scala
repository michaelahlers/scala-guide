package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.setup

import scala.concurrent.Future

trait UserService {

  def getUsers(request: GetUsersRequest): Future[GetUsersResponse]

}
