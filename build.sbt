val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  scalaVersion := "3.3.1",
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-language:implicitConversions",
    "-Xfatal-warnings",
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    name := "scala-redis-sample",
    libraryDependencies ++= Seq(
      "redis.clients" % "jedis" % "5.1.0",
      "io.lettuce" % "lettuce-core" % "6.3.0.RELEASE",
      "org.redisson" % "redisson" % "3.25.0",
      "org.slf4j" % "slf4j-api" % "1.7.36",
      "ch.qos.logback" % "logback-classic" % "1.4.14"
    ),
  )
