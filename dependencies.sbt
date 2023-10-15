ThisBuild / scalaVersion := "2.12.18"
ThisBuild / crossScalaVersions ++=
  "2.13.11" ::
    Nil

/**
 * Cats is a library which provides abstractions for functional programming in the Scala programming language.
 * @see [[https://typelevel.org/cats/]]
 */
ThisBuild / libraryDependencies +=
  "org.typelevel" %% "cats-core" % "2.10.0"

/**
 * Patch and modify deeply nested case classes.
 *
 * @see [[https://github.com/softwaremill/quicklens]]
 */
ThisBuild / libraryDependencies ++=
  "com.softwaremill.quicklens" %% "quicklens" % "1.9.6" ::
    Nil

/**
 * A Scala library to pretty-print values and types.
 * @see [[https://github.com/com-lihaoyi/PPrint]]
 */
ThisBuild / libraryDependencies +=
  "com.lihaoyi" %% "pprint" % "0.7.0"

/**
 * ScalaTest is the most flexible and most popular testing tool in the Scala ecosystem.
 * @see [[https://www.scalatest.org/]]
 */
ThisBuild / libraryDependencies ++=
  "org.scalatest" %% "scalatest" % "3.2.10" % Test ::
    Nil

/**
 * Native Scala mocking framework.
 * @see [[https://www.scalatest.org/]]
 */
ThisBuild / libraryDependencies ++=
  "org.scalamock" %% "scalamock" % "5.2.0" % Test ::
    Nil

/**
 * Readable deltas for Scala case classes.
 * @see [[https://github.com/softwaremill/diffx]]
 */
ThisBuild / libraryDependencies ++=
  "com.softwaremill.diffx" %% "diffx-scalatest-must" % "0.8.3" % Test ::
    "com.softwaremill.diffx" %% "diffx-scalatest-should" % "0.8.3" % Test ::
    Nil
