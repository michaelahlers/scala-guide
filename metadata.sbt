name := "Scala Examples"
description := "Practical solutions for the working professional."

ThisBuild / organization := "ahlers.scala.examples"
ThisBuild / organizationName := "Michael Ahlers"

ThisBuild / startYear := Some(2023)

ThisBuild / developers :=
  Developer("michaelahlers", "Michael Ahlers", "michael@ahlers.consulting", url("http://github.com/michaelahlers")) ::
    Nil

ThisBuild / scmInfo :=
  Some(ScmInfo(
    browseUrl = url("https://github.com/michaelahlers/scala-examples"),
    connection = "https://github.com/michaelahlers/scala-examples.git",
    devConnection = Some("git@github.com:michaelahlers/scala-examples.git")
  ))

ThisBuild / licenses += "MIT" -> new URI("http://opensource.org/licenses/MIT").toURL
