package caseStudy.betterInterfaceDesignWithTypes.version1

sealed trait Expression[+A]
object Expression {

  /** Matches [[value]] exactly. */
  case class Exact[A](value: A) extends Expression[A]

  /** Anything will match. */
  case object Wildcard extends Expression[Nothing]

  /** Matches when not set. */
  case object IsNull extends Expression[Nothing]

}
