import sbt._
import Keys._

object Common {

  val baseName = "multiproject-slick"

  val settings: Seq[Setting[_]] = {
    version := "1.0.0"
  }

  val commonDependencies = Seq(
    "com.typesafe.play"  %% "play-cache"           % "2.2.2-RC2",
    "com.typesafe.slick" %% "slick"                % "1.0.1",
    "com.typesafe.play"  %% "play-slick"           % "0.5.0.8",
    "mysql"              %  "mysql-connector-java" % "5.1.27"
  )
}