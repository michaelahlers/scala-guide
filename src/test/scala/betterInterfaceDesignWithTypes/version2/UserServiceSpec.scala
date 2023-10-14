package betterInterfaceDesignWithTypes.version2

import betterInterfaceDesignWithTypes.version2.Expression.Exact
import betterInterfaceDesignWithTypes.version2.Expression.IsNull
import betterInterfaceDesignWithTypes.version2.GetUsersRequest.Predicate.ByCity
import betterInterfaceDesignWithTypes.version2.GetUsersRequest.Predicate.ByEmailAddress
import betterInterfaceDesignWithTypes.version2.GetUsersRequest.Predicate.ByFamilyName
import betterInterfaceDesignWithTypes.version2.GetUsersRequest.Predicate.ByGivenName
import betterInterfaceDesignWithTypes.version2.GetUsersRequest.Predicate.ByRegion
import org.scalamock.scalatest.MockFactory
import org.scalatest.funspec.FixtureAnyFunSpec

class UserServiceSpec extends FixtureAnyFunSpec with MockFactory {
  import UserServiceSpec._

  it("Likely returns that one famous user.") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(GetUsersRequest(
        ByGivenName(Exact("Grace")) &
          ByFamilyName(Exact("Hopper")),
      ))
  }

  it("Returns that Grace Hopper in particular!") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(GetUsersRequest(
        ByEmailAddress(Exact("grace.hopper@navy.mil")),
      ))
  }

  it("Might return her from here or there.") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(GetUsersRequest(
        (ByCity(Exact("Arlington")) & ByRegion(Exact("Virginia"))) |
          (ByCity(Exact("New York")) & ByRegion(Exact("New York"))),
      ))
  }

  it("Finds anyone who hasn't set an email address yet.") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(GetUsersRequest(
        ByEmailAddress(IsNull),
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
