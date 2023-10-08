package examples.basic.algebraicDataTypeEssentails.interfaceDesignImprovements.attempt1

sealed trait Argument[+A]
object Argument {

  /** Matches [[value]] exactly. */
  case class Exact[A](value: A) extends Argument[A]

  /** Anything will match. */
  case object Wildcard extends Argument[Nothing]

  /** Matches when not set. */
  case object IsNull extends Argument[Nothing]

}
