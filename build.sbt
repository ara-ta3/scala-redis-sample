organization := "ara_ta3"

name := "acala-redis-sample"

version := "0.1.0-SNAPSHOT"

scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
    "redis.clients" % "jedis" % "5.1.0",
    "io.lettuce" % "lettuce-core" % "6.3.0.RELEASE",
    "org.redisson" % "redisson" % "3.25.0"
)

