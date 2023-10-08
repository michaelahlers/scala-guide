package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.setup

import scala.concurrent.Future

case class LocalUserService() extends UserService {
  override def getUsers(request: GetUsersRequest): Future[GetUsersResponse] = {
    pprint.log(request)
    Future.successful(GetUsersResponse(
      users = Nil,
    ))
  }

}
