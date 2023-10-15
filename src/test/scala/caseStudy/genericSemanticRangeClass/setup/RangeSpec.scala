package caseStudy.genericSemanticRangeClass.setup

import java.time.LocalDate
import org.scalatest.Inside._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class RangeSpec extends AnyFlatSpec {
  import RangeSpec.samples

  it should "represent left-bounded ranges" in {
    inside(samples.leftBounded) {
      case Range(Some(minimum), None) =>
        minimum.shouldBe(samples.left)
    }
  }

  it should "represent right-bounded ranges" in {
    inside(samples.rightBounded) {
      case Range(None, Some(maximum)) =>
        maximum.shouldBe(samples.right)
    }
  }

  it should "represent bounded ranges" in {
    inside(samples.bounded) {
      case Range(Some(minimum), Some(maximum)) =>
        minimum.shouldBe(samples.left)
        maximum.shouldBe(samples.right)
    }
  }

  it can "represent unbounded ranges" in {
    inside(samples.bounded) {
      case Range(None,None) =>
    }
  }

}

object RangeSpec {

  object samples {

    val left: LocalDate = LocalDate.of(2023, 1, 1)
    val right: LocalDate = left.plusDays(10)

    val leftBounded = Range(Some(left), None)
    val rightBounded = Range(None, Some(right))
    val bounded = Range(Some(left), Some(right))

  }

}
