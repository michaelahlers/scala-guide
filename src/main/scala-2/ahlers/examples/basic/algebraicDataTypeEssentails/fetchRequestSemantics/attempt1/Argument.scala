package ahlers.examples.basic.algebraicDataTypeEssentails.fetchRequestSemantics.attempt1

sealed trait Argument
object Argument {

  /** Matches [[text]] exactly. */
  case class Exact(text: String) extends Argument

  /** Matches where [[text]] is a substring. */
  case class Contains(text: String) extends Argument

  /** Any record value will match. */
  case object Wildcard extends Argument

  /** Requires the value to not be set. */
  case object IsNull extends Argument

}
