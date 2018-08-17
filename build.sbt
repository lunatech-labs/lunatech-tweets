name := "Lunatech-tweets"

version := "1.0"

scalaVersion := "2.12.4"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "com.danielasfregola"      %% "twitter4s"      % "5.5",
  "org.apache.logging.log4j" % "log4j-api"       % "2.11.1",
  "ch.qos.logback"           % "logback-classic" % "1.2.3",
  "com.softwaremill.sttp"    %% "core"           % "1.3.0" // scala http client
)
