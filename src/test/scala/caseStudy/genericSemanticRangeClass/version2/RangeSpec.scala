package caseStudy.genericSemanticRangeClass.version2

import caseStudy.genericSemanticRangeClass.version2.Range.Bounded
import caseStudy.genericSemanticRangeClass.version2.Range.LeftBounded
import caseStudy.genericSemanticRangeClass.version2.Range.RightBounded
import java.time.LocalDate
import org.scalactic.Bad
import org.scalactic.Good
import org.scalatest.Inside._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import syntax._

class RangeSpec extends AnyFlatSpec {
  import RangeSpec.orderingLocalDate
  import RangeSpec.samples

  it should "represent left-bounded ranges" in {
    inside(samples.leftBounded) {
      case LeftBounded(minimum) =>
        minimum shouldBe samples.today
    }
  }

  it should "represent right-bounded ranges" in {
    inside(samples.rightBounded) {
      case RightBounded(right) =>
        right shouldBe samples.nextWeek
    }
  }

  it should "represent bounded ranges" in {
    inside(samples.bounded) {
      case Bounded(minimum, maximum) =>
        minimum shouldBe samples.today
        maximum shouldBe samples.nextWeek
    }
  }

  it should "not bound unordered types" in {

    /** Phantom type that doesn't have an [[Ordering]]. */
    trait Value
    def left: Value = ???
    def right: Value = ???

    """Bounded(left, right)""" shouldNot compile
  }

  it should "not permit mismatched minimum and maximum bounds" in {
    an[IllegalArgumentException] shouldBe thrownBy {
      Bounded(samples.nextWeek, samples.today)
    }
  }

  it should "report bounding error as value" in {
    Bounded.of(samples.nextWeek, samples.today) shouldBe a[Bad[_]]
  }

  it should "provide syntax for constructing ranges" in {
    inside(samples.today.minimum) {
      case LeftBounded(minimum) =>
        minimum shouldBe samples.today
    }

    inside(samples.nextWeek.maximum) {
      case RightBounded(maximum) =>
        maximum shouldBe samples.nextWeek
    }

    inside(samples.today.bounded(samples.nextWeek)) {
      case Good(Bounded(minimum, maximum)) =>
        minimum shouldBe samples.today
        maximum shouldBe samples.nextWeek
    }
  }

}

object RangeSpec {

  object samples {

    val today: LocalDate = LocalDate.now()
    val nextWeek: LocalDate = today.plusDays(7)

    val leftBounded: Range[LocalDate] = LeftBounded(today)
    val rightBounded: Range[LocalDate] = RightBounded(nextWeek)
    val bounded: Range[LocalDate] = Bounded(today, nextWeek)

  }

  /**
   * Scala doesn't provide an [[Ordering]] of [[LocalDate]] out-of-the-box. Luckily, it's trivial to make one since [[LocalDate]] is a [[java.lang.Comparable]].
   */
  implicit val orderingLocalDate: Ordering[LocalDate] = { (x: LocalDate, y: LocalDate) =>
    x.compareTo(y)
  }

}
