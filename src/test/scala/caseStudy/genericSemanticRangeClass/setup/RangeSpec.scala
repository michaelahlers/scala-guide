package caseStudy.genericSemanticRangeClass.setup

import java.time.LocalDate
import org.scalatest.Inside._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class RangeSpec extends AnyFlatSpec {
  import RangeSpec.samples

  it should "represent left-bounded ranges" in {
    inside(samples.leftBounded) {
      case Range(Some(left), None) =>
        left.shouldBe(samples.left)
    }
  }

  it should "represent right-bounded ranges" in {
    inside(samples.rightBounded) {
      case Range(None, Some(right)) =>
        right.shouldBe(samples.right)
    }
  }

  it should "represent bounded ranges" in {
    inside(samples.bounded) {
      case Range(Some(left), Some(right)) =>
        left.shouldBe(samples.left)
        right.shouldBe(samples.right)
    }
  }

  /**
   * Yes, [[Range]] ''can'' represent this state, but should it? Does it make sense for our business needs? Is this an error case tempting consumers to `throw` an [[IllegalArgumentException exception]]?
   */
  it can "represent unbounded ranges" in {
    inside(samples.unbounded) {
      case Range(None, None) =>
    }
  }

}

object RangeSpec {

  object samples {

    val left: LocalDate = LocalDate.of(2023, 1, 1)
    val right: LocalDate = left.plusDays(10)

    val leftBounded: Range[LocalDate] = Range(Some(left), None)
    val rightBounded: Range[LocalDate] = Range(None, Some(right))
    val bounded: Range[LocalDate] = Range(Some(left), Some(right))
    val unbounded: Range[Nothing] = Range(None, None)

  }

}
