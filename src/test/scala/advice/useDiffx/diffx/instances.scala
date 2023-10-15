package advice.useDiffx.diffx

import advice.useDiffx.Fulfillment
import com.softwaremill.diffx.Diff
import com.softwaremill.diffx.generic.auto._

object instances {

  implicit val diffFulfillment: Diff[Fulfillment] =
    Diff.summon[Fulfillment]

}
