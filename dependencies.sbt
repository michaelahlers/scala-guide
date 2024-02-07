ThisBuild / scalaVersion := "2.13.12"
ThisBuild / crossScalaVersions :=
  "2.12.18" ::
    "2.13.12" ::
    Nil

/**
 * Cats is a library which provides abstractions for functional programming in the Scala programming language.
 * @see [[https://typelevel.org/cats/]]
 */
libraryDependencies +=
  "org.typelevel" %% "cats-core" % "2.10.0"

/**
 * Patch and modify deeply nested case classes.
 *
 * @see [[https://github.com/softwaremill/quicklens]]
 */
libraryDependencies +=
  "com.softwaremill.quicklens" %% "quicklens" % "1.9.6"

/**
 * A Scala library to pretty-print values and types.
 * @see [[https://github.com/com-lihaoyi/PPrint]]
 */
libraryDependencies +=
  "com.lihaoyi" %% "pprint" % "0.8.1"

/**
 * Scalactic provides constructs related to quality that are useful in both production code and tests.
 * @see [[https://www.scalactic.org/]]
 */
libraryDependencies +=
  "org.scalactic" %% "scalactic" % "3.2.17"

/**
 * ScalaTest is the most flexible and most popular testing tool in the Scala ecosystem.
 * @see [[https://www.scalatest.org/]]
 */
libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.2.18" % Test

/**
 * Native Scala mocking framework.
 * @see [[https://www.scalatest.org/]]
 */
libraryDependencies ++=
  "org.scalamock" %% "scalamock" % "5.2.0" % Test ::
    Nil

/**
 * Readable deltas for Scala case classes.
 * @see [[https://github.com/softwaremill/diffx]]
 */
libraryDependencies ++=
  "com.softwaremill.diffx" %% "diffx-scalatest-must" % "0.9.0" % Test ::
    "com.softwaremill.diffx" %% "diffx-scalatest-should" % "0.8.3" % Test ::
    Nil
