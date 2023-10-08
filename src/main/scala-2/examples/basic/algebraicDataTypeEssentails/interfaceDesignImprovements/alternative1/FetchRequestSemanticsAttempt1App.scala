package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.alternative1

object FetchRequestSemanticsAttempt1App extends App {
  import Argument._
  import GetUsersRequest._

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

  /** Added bonus, who hasn't set an email address yet? */
  userService
    .getUsers(ByContactInformation(
      emailAddress = IsNull,
      phoneNumber = Wildcard,
    ))

}
