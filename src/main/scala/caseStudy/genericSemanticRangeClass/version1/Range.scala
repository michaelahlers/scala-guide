package caseStudy.genericSemanticRangeClass.version1

sealed trait Range[A]
object Range {

  case class LeftBounded[A](
    minimum: A,
  ) extends Range[A]

  case class RightBounded[A](
    maximum: A,
  ) extends Range[A]

  case class Bounded[A](
    minimum: A,
    maximum: A,
  ) extends Range[A]

}
