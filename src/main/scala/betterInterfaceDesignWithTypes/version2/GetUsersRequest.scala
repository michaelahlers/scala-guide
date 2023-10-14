package betterInterfaceDesignWithTypes.version2

import GetUsersRequest.Predicate
import cats.data.NonEmptyList

case class GetUsersRequest(predicate: Predicate)
object GetUsersRequest {
  import Expression._

  sealed trait Predicate
  object Predicate {

    /** This property isn't nullable, so any expression must be [[NonNull]] (''i.e.'', [[IsNull]] isn't allowed). */
    case class ByGivenName(expression: Expression[String] with NonNull) extends Predicate

    /** This property isn't nullable, so any expression must be [[NonNull]] (''i.e.'', [[IsNull]] isn't allowed). */
    case class ByFamilyName(expression: Expression[String] with NonNull) extends Predicate

    case class ByEmailAddress(expression: Expression[String]) extends Predicate

    case class ByPhoneNumber(expression: Expression[String]) extends Predicate

    case class ByCity(expression: Expression[String]) extends Predicate

    case class ByRegion(expression: Expression[String]) extends Predicate

    case class Or(predicates: NonEmptyList[Predicate]) extends Predicate
    case class And(predicates: NonEmptyList[Predicate]) extends Predicate

    implicit class Ops(private val self: Predicate) extends AnyVal {

      def or(other: Predicate): Predicate = self match {
        case self: Or => Or(self.predicates :+ other)
        case self => Or(NonEmptyList.of(self, other))
      }

      def and(other: Predicate): Predicate = self match {
        case self: And => And(self.predicates :+ other)
        case self => Or(NonEmptyList.of(self, other))
      }

      def |(other: Predicate): Predicate = or(other)
      def &(other: Predicate): Predicate = and(other)

    }

  }

}
