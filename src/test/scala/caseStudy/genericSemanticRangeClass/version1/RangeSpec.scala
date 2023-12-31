package caseStudy.genericSemanticRangeClass.version1

import caseStudy.genericSemanticRangeClass.version1.Range.Bounded
import caseStudy.genericSemanticRangeClass.version1.Range.LeftBounded
import caseStudy.genericSemanticRangeClass.version1.Range.RightBounded
import java.time.LocalDate
import org.scalatest.Inside._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class RangeSpec extends AnyFlatSpec {
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

}

object RangeSpec {

  object samples {

    val minimum: LocalDate = LocalDate.of(2023, 1, 1)
    val maximum: LocalDate = minimum.plusDays(10)

    val leftBounded: Range[LocalDate] = LeftBounded(minimum)
    val rightBounded: Range[LocalDate] = RightBounded(maximum)
    val bounded: Range[LocalDate] = Bounded(minimum, maximum)

  }

}
