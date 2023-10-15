package advice.useDiffx

import advice.useDiffx.Product.Kind
import advice.useDiffx.Product.Make
import advice.useDiffx.Product.Model
import advice.useDiffx.Product.Year

case class Product(
  kind: Kind,
  make: Make,
  model: Model,
  year: Year,
)

object Product {

  case class Kind(toText: String) extends AnyVal
  case class Make(toText: String) extends AnyVal
  case class Model(toText: String) extends AnyVal
  case class Year(toInt: String) extends AnyVal

}
