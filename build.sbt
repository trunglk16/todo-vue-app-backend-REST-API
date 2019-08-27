
import sbt._
import Keys._
import Dependencies._


name := "todo-vue-backend-REST-API"

version := "1.0"

lazy val commonSettings = Seq(
  resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
  resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  libraryDependencies ++= Seq(jdbc, ehcache, ws,filters, specs2 % Test, guice),
  scalaVersion := "2.12.2"
)

lazy val subProject = Seq(
  scalaSource in Compile := baseDirectory.value / "src" / "main" / "scala",
  scalaSource in Test := baseDirectory.value / "src" / "test" / "scala",
  resourceDirectory in Compile := baseDirectory.value / "src" / "main" / "resources",
  resourceDirectory in Test := baseDirectory.value / "src" / "test" / "resources"
)

lazy val root = (project in file("."))
  .aggregate(utility, domain, application, port)
  .dependsOn(utility, domain, application  % "compile->compile", port)
  .settings(commonSettings)
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

lazy val port = (project in file("todoVue/port"))
  .aggregate(portDatabase, portHttp)
  .dependsOn(portDatabase, portHttp)
  .settings(commonSettings)
  .settings(subProject)

lazy val portDatabase = (project in file("todoVue/port/secondary/database"))
  .dependsOn(utility, application  % "compile->compile")
  .settings(commonSettings)
  .settings(subProject)
  .settings(libraryDependencies ++= portDatabaseDependencies)
  .enablePlugins(FlywayPlugin)
  .settings(
    flywayLocations := Seq("filesystem:todoVue/port/secondary/database/src/main/resources/db.migration.default")
  )

lazy val portHttp = (project in file("todoVue/port/primary/http"))
  .dependsOn(utility, application  % "compile->compile")
  .settings(commonSettings)
  .settings(subProject)
  .enablePlugins(PlayScala)
  .enablePlugins(FlywayPlugin)
  .settings(libraryDependencies ++= Seq(playJson4s, playJson4sTest, filters, json4s))

lazy val application = (project in file("todoVue/application"))
  .dependsOn(utility, domain)
  .settings(commonSettings)
  .settings(subProject)
  .settings(libraryDependencies ++= Seq(circeCore, circeGeneric, circeParser, playCirce, googleApiClient, googleApiServicesOauth2))

lazy val domain = (project in file("todoVue/domain"))
  .dependsOn(utility)
  .settings(commonSettings)
  .settings(subProject)

lazy val utility = Project(
  id = "todoVue-utility",
  base = file("todoVue/utility")
).settings(commonSettings)
  .settings(subProject)
  .settings(libraryDependencies ++= Seq(playJson4s, playJson4sTest, json4s))

unmanagedResourceDirectories in Test  <+=  baseDirectory ( _ /"target/web/public/test" )
