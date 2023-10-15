package caseStudy.betterInterfaceDesignWithTypes.version1

import caseStudy.betterInterfaceDesignWithTypes.version1.Expression.Exact
import caseStudy.betterInterfaceDesignWithTypes.version1.Expression.IsNull
import caseStudy.betterInterfaceDesignWithTypes.version1.Expression.Wildcard
import caseStudy.betterInterfaceDesignWithTypes.version1.GetUsersRequest.ByContactInformation
import caseStudy.betterInterfaceDesignWithTypes.version1.GetUsersRequest.ByLocale
import caseStudy.betterInterfaceDesignWithTypes.version1.GetUsersRequest.ByPersonName
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
      .getUsers(ByPersonName(
        givenName = Exact("Grace"),
        familyName = Exact("Hopper"),
      ))
  }

  it("Returns that Grace Hopper in particular!") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(ByContactInformation(
        emailAddress = Exact("grace.hopper@navy.mil"),
        phoneNumber = Wildcard,
      ))
  }

  it("Might return her.") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(ByLocale(
        city = Exact("Arlington"),
        region = Exact("Virginia"),
      ))
  }

  it("Finds anyone who hasn't set an email address yet.") { fixtures =>
    import fixtures._

    (userService.getUsers _)
      .expects(*)
      .returns(GetUsersResponse())
      .once()

    userService
      .getUsers(ByContactInformation(
        emailAddress = IsNull,
        phoneNumber = Wildcard,
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
