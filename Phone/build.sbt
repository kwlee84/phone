name := """Phone"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

//resolvers += "Local Maven Repository" at "file:/D:/Project/Phone/Repository"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "commons-io" % "commons-io" % "2.4",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc4"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
