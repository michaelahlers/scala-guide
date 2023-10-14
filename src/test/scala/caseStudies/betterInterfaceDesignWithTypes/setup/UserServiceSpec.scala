package caseStudies.betterInterfaceDesignWithTypes.setup

import org.scalamock.scalatest.MockFactory
import org.scalatest.funspec.FixtureAnyFunSpec

class UserServiceSpec extends FixtureAnyFunSpec with MockFactory {
  import UserServiceSpec._

  it("Is reasonable to expect, perhaps, one famous user.") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(GetUsersRequest(
        givenName = Some("Grace"),
        familyName = Some("Hopper"),
      ))
  }

  it("""Is also reasonable to expect all users named "Grace" are returned."""") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(GetUsersRequest(
        givenName = Some("Grace"),
        familyName = None,
      ))
  }

  it("""Is unclear if no users returned or all of them. (Should that even be allowed? Probably not!)."""") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(GetUsersRequest(
        givenName = None,
        familyName = None,
      ))
  }

  override protected type FixtureParam = Fixtures
  override protected def withFixture(test: OneArgTest) = {
    val userService = mock[UserService]
    withFixture(test.toNoArgTest(Fixtures(
      userService = userService,
    )))
  }
}

object UserServiceSpec {
  case class Fixtures(
    userService: UserService,
  )

}
