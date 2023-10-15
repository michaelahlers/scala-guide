package caseStudy.genericSemanticRangeClass.version2

import org.scalactic.Bad
import org.scalactic.ErrorMessage
import org.scalactic.Good
import org.scalactic.Or
import org.scalactic.Requirements.require
import scala.math.Ordering.Implicits.infixOrderingOps
import scala.util.control.NonFatal

sealed trait Range[A]
object Range {

  case class LeftBounded[A](
    minimum: A,
  ) extends Range[A]

  case class RightBounded[A](
    maximum: A,
  ) extends Range[A]

  /**
   * @throws [[IllegalArgumentException]] if `minimum` is greater than `maximum`.
   */
  case class Bounded[A: Ordering](
    minimum: A,
    maximum: A,
  ) extends Range[A] {
    require(minimum <= maximum)
  }

  /**
   * @return A [[Good]] [[Bounded]] if `minimum` is less than or equal to `maximum`; otherwise, a [[Bad]].
   */
  object Bounded {
    def of[A: Ordering](
      minimum: A,
      maximum: A,
    ): Range[A] Or ErrorMessage =
      try Good(Bounded(minimum, maximum))
      catch {
        case NonFatal(cause) =>
          Bad(cause.getMessage)
      }
  }

}
