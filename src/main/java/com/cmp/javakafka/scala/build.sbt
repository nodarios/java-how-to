/*
name := "ProjectName"
version := "0.1"

scalaVersion := "2.11.12"
val sparkVersion = "2.3.0"
val fmVersion = "0.7.4-SNAPSHOT"

// removes Central Maven Repository from resolving the dependencies 
externalResolvers --= Seq(Resolver.DefaultMavenRepository)

// add Nexus as Resolver for dependencies. If Maven Central Repository is required, Nexus takes care on that!
resolvers ++= Seq("Maven-Public" at "http://DN/repository/maven-public/")

// Ad dependencies
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "com.cmp.fm" %% "fm_2-3-0" % fmVersion,
  "com.kerb4j" % "kerb4j-client" % "0.0.8"
)
*/
