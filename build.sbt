name := "polymesh"

lazy val commonSettings = Seq(
  organization := "com.maxsitu",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.12.1",
  scalacOptions += "-deprecation",
  scalacOptions += "-feature",
  resolvers ++= Seq(Resolver.sonatypeRepo("snapshots"), Resolver.jcenterRepo)
)

addCommandAlias("mgm", "migration_manager/run")
addCommandAlias("mg", "migrations/run")

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.8"
lazy val slickVersion = "3.2.3"
lazy val forkliftVersion = "0.3.1"

lazy val slick = "com.typesafe.slick" %% "slick" % slickVersion
lazy val slickHikariCp = "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
lazy val postgres = "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
lazy val forkliftSlick = "com.liyaos" %% "scala-forklift-slick" % forkliftVersion
lazy val forkliftGitTools = "com.liyaos" %% "scala-forklift-git-tools" % forkliftVersion
lazy val slf4j = "org.slf4j" % "slf4j-nop" % "1.6.4"

/**
  * Helper projects declared
  */
lazy val generatedCode = (project in file("generated_code"))
  .settings(
    commonSettings: _*
  )
  .settings(
    libraryDependencies ++= Seq(slick)
  )

lazy val migrationManager = (project in file("migration_manager"))
  .settings(
    commonSettings: _*
  )
  .settings(
    libraryDependencies ++= Seq(slickHikariCp, postgres, forkliftSlick)
  )

lazy val migrations = (project in file("migrations"))
  .dependsOn(generatedCode, migrationManager)
  .settings(
    commonSettings: _*
  )
  .settings(
    libraryDependencies ++= Seq(postgres, forkliftSlick, slf4j)
  )

lazy val gitTools = Project("git-tools", file("tools/git"))
  .settings(commonSettings: _*)
  .settings {
    libraryDependencies ++= Seq(
      forkliftSlick,
      forkliftGitTools,
      "com.typesafe" % "config" % "1.3.3",
      "org.eclipse.jgit" % "org.eclipse.jgit" % "5.4.0.201906121030-r"
    )
  }
/**
  * Application dependencies
  */

lazy val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

/**
  * Application projects declared
  */

lazy val `polymesh` = (project in file("."))
  .aggregate(
    `polymesh-api`, `polymesh-impl`, `migrations`,
    `migrationManager`, `generatedCode`, `gitTools`
  )
  .settings(
    commonSettings: _*
  )

lazy val `polymesh-api` = (project in file("polymesh-api"))
  .settings(
    commonSettings: _*
  )
  .settings(
    libraryDependencies ++= Seq(lagomScaladslApi)
  )

lazy val `polymesh-impl` = (project in file("polymesh-impl"))
  .enablePlugins(LagomScala)
  .settings(
    commonSettings: _*
  )
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra, lagomScaladslKafkaBroker,
      lagomScaladslTestKit, macwire, scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`polymesh-api`)

lazy val `auth-api` = (project in file("auth-api"))
  .settings(
    commonSettings: _*
  )
  .settings(
    libraryDependencies ++= Seq(lagomScaladslApi)
  )


lazy val `auth-impl` = (project in file("auth-impl"))
  .enablePlugins(LagomScala)
  .settings(
    commonSettings: _*
  )
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceJdbc, lagomScaladslKafkaBroker,
      lagomScaladslTestKit, macwire, scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`auth-api`)