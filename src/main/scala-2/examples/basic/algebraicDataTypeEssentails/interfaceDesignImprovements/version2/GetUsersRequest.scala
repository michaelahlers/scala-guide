package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.version2

import examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.version2.GetUsersRequest.Predicate

case class GetUsersRequest(predicate: Predicate)
object GetUsersRequest {

  sealed trait Predicate
  object Predicate {
    case class ByGivenName(expression: Expression[String]) extends Predicate
    case class ByFamilyName(expression: Expression[String]) extends Predicate
    case class ByEmailAddress(expression: Expression[String]) extends Predicate
    case class ByPhoneNumber(expression: Expression[String]) extends Predicate
    case class ByCity(expression: Expression[String]) extends Predicate
    case class ByRegion(expression: Expression[String]) extends Predicate

    case class Or(predicates: Seq[Predicate]) extends Predicate
    case class And(predicates: Seq[Predicate]) extends Predicate

    implicit class Ops(private val self: Predicate) extends AnyVal {

      def or(other: Predicate): Predicate = self match {
        case self: Or => Or(self.predicates :+ other)
        case self => Or(Seq(self, other))
      }

      def and(other: Predicate): Predicate = self match {
        case self: And => And(self.predicates :+ other)
        case self => Or(Seq(self, other))
      }

      def |(other: Predicate): Predicate = or(other)
      def &(other: Predicate): Predicate = and(other)

    }

  }

}
