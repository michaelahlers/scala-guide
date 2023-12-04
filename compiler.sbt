import org.typelevel.scalacoptions.ScalacOptions

tpolecatScalacOptions += ScalacOptions.privatePartialUnification

Test / tpolecatExcludeOptions += ScalacOptions.warnUnusedLocals
Test / tpolecatExcludeOptions += ScalacOptions.warnNonUnitStatement
Test / tpolecatExcludeOptions += ScalacOptions.warnValueDiscard

Test / tpolecatExcludeOptions += ScalacOptions.privateWarnUnusedLocals
Test / tpolecatExcludeOptions += ScalacOptions.privateWarnValueDiscard
