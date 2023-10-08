package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.attempt1

object FetchRequestSemanticsAttempt1App extends App {
  import GetUsersRequest._

  val userService: UserService = LocalUserService()

  /** Still likely returns that one famous user. */
  userService
    .getUsers(ByPersonName(
      givenName = Some("Grace"),
      familyName = Some("Hopper"),
    ))

  /** ''That'' Grace Hopper in particular! */
  userService
    .getUsers(ByContactInformation(
      emailAddress = Some("grace.hopper@navy.mil"),
      phoneNumber = None,
    ))

  /** She might be here. */
  userService
    .getUsers(ByLocale(
      city = Some("Arlington"),
      region = Some("Virginia"),
    ))

}
