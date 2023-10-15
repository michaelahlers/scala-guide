package caseStudy.genericSemanticRangeClass.setup

case class Range[A](
  minimum: Option[A],
  maximum: Option[A],
)
