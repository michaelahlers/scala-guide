package betterInterfaceDesignWithTypes.version2

sealed trait Expression[+A]
object Expression {

  sealed trait NonNull

  /** Matches [[value]] exactly. */
  case class Exact[A](value: A) extends Expression[A] with NonNull

  /** Matches when not set. */
  case object IsNull extends Expression[Nothing]

}
