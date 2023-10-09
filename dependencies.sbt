ThisBuild / scalaVersion := "2.12.18"
ThisBuild / crossScalaVersions ++=
  "2.13.11" ::
    Nil

ThisBuild / libraryDependencies +=
  "org.typelevel" %% "cats-core" % "2.10.0"

ThisBuild / libraryDependencies +=
  "com.lihaoyi" %% "pprint" % "0.7.0"

ThisBuild / libraryDependencies ++=
  "com.softwaremill.diffx" %% "diffx-scalatest-must" % "0.8.3" % Test ::
    "com.softwaremill.diffx" %% "diffx-scalatest-should" % "0.8.3" % Test ::
    "org.scalatest" %% "scalatest" % "3.2.10" % Test ::
    Nil
