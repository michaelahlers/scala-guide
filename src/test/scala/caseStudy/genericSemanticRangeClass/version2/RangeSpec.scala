package caseStudy.genericSemanticRangeClass.version2

import caseStudy.genericSemanticRangeClass.version2.Range.Bounded
import caseStudy.genericSemanticRangeClass.version2.Range.LeftBounded
import caseStudy.genericSemanticRangeClass.version2.Range.RightBounded
import java.time.LocalDate
import org.scalactic.Bad
import org.scalatest.Inside._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class RangeSpec extends AnyFlatSpec {
  import RangeSpec.orderingLocalDate
  import RangeSpec.samples

  it should "represent left-bounded ranges" in {
    inside(samples.leftBounded) {
      case LeftBounded(minimum) =>
        minimum.shouldBe(samples.minimum)
    }
  }

  it should "represent right-bounded ranges" in {
    inside(samples.rightBounded) {
      case RightBounded(right) =>
        right.shouldBe(samples.maximum)
    }
  }

  it should "represent bounded ranges" in {
    inside(samples.bounded) {
      case Bounded(minimum, maximum) =>
        minimum.shouldBe(samples.minimum)
        maximum.shouldBe(samples.maximum)
    }
  }

  it should "not bound unordered types" in {

    /** Phantom type that doesn't have an [[Ordering]]. */
    trait Value
    def left: Value = ???
    def right: Value = ???

    """Bounded(left, right)""".shouldNot(compile)
  }

  it should "not permit mismatched minimum and maximum bounds" in {
    an[IllegalArgumentException] shouldBe thrownBy {
      Bounded(samples.maximum, samples.minimum)
    }
  }

  it should "foo" in {
    Bounded.of(samples.maximum, samples.minimum) shouldBe a[Bad[_]]
  }

}

object RangeSpec {

  object samples {

    val minimum: LocalDate = LocalDate.of(2023, 1, 1)
    val maximum: LocalDate = minimum.plusDays(10)

    val leftBounded: Range[LocalDate] = LeftBounded(minimum)
    val rightBounded: Range[LocalDate] = RightBounded(maximum)
    val bounded: Range[LocalDate] = Bounded(minimum, maximum)

  }

  /**
   * Scala doesn't provide an [[Ordering]] of [[LocalDate]] out-of-the-box. Luckily, it's trivial to make one since [[LocalDate]] is a [[java.lang.Comparable]].
   */
  implicit val orderingLocalDate: Ordering[LocalDate] = { (x: LocalDate, y: LocalDate) =>
    x.compareTo(y)
  }

}
