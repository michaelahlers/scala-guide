name := "Scala Guide"
description := "Helpful recommendations and oractical solutions for the working professional."

ThisBuild / organization := "ahlers.scala-guide"
ThisBuild / organizationName := "Michael Ahlers"

ThisBuild / startYear := Some(2023)

ThisBuild / developers :=
  Developer("michaelahlers", "Michael Ahlers", "michael@ahlers.consulting", url("http://github.com/michaelahlers")) ::
    Nil

ThisBuild / scmInfo :=
  Some(ScmInfo(
    browseUrl = url("https://github.com/michaelahlers/scala-guide"),
    connection = "https://github.com/michaelahlers/scala-guide.git",
    devConnection = Some("git@github.com:michaelahlers/scala-guide.git"),
  ))

ThisBuild / licenses += "MIT" -> new URI("http://opensource.org/licenses/MIT").toURL
