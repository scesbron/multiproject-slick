Common.settings

name := Common.baseName

libraryDependencies ++= Common.commonDependencies

lazy val core = project

lazy val main = project.in(file("."))
  .dependsOn(core).aggregate(core)

playScalaSettings
