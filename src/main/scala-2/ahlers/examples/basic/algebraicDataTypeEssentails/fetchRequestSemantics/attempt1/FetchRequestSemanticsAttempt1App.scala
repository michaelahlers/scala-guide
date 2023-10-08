package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.attempt1

object FetchRequestSemanticsAttempt1App extends App {
  import GetUsersRequest._
  import Argument._

  val userService: UserService = LocalUserService()

  /** Still likely returns that one famous user. */
  userService
    .getUsers(ByPersonName(
      givenName = Exact("Grace"),
      familyName = Exact("Hopper"),
    ))

  /** ''That'' Grace Hopper in particular! */
  userService
    .getUsers(ByContactInformation(
      emailAddress = Exact("grace.hopper@navy.mil"),
      phoneNumber = Wildcard,
    ))

  /** She might be here. */
  userService
    .getUsers(ByLocale(
      city = Exact("Arlington"),
      region = Exact("Virginia"),
    ))

}
