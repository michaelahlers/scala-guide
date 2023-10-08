package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.setup

object ScenariosApp {

  val userService: UserService = LocalUserService()

  userService
    .getUsers(GetUsersRequest(
      givenName = Some("Grace"),
      familyName = Some("Hopper"),
    ))

}
