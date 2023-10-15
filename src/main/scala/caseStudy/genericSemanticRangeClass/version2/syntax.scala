package caseStudy.genericSemanticRangeClass.version2

import caseStudy.genericSemanticRangeClass.version2.Range.Bounded
import caseStudy.genericSemanticRangeClass.version2.Range.LeftBounded
import caseStudy.genericSemanticRangeClass.version2.Range.RightBounded
import org.scalactic.ErrorMessage
import org.scalactic.Or

object syntax {

  implicit class RangeValueOps[A](private val self: A) extends AnyVal {

    def minimum: LeftBounded[A] =
      LeftBounded(self)

    def maximum: RightBounded[A] =
      RightBounded(self)

    def bounded(by: A)(implicit A: Ordering[A]): Bounded[A] Or ErrorMessage =
      Bounded.of(self, by)

  }

}
