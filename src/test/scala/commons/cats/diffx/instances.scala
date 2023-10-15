package commons.cats.diffx

import cats.data.NonEmptyList
import com.softwaremill.diffx.Diff

object instances {

  implicit def diffNonEmptyList[A: Diff]: Diff[NonEmptyList[A]] =
    Diff[Seq[A]].contramap(_.toList)

}
