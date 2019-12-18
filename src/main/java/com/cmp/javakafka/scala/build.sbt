/*
name := "ProjectName"
version := "0.1"

scalaVersion := "2.11.12"
val sparkVersion = "2.3.0"
val fmVersion = "0.7.4-SNAPSHOT"
val jacksonVersion = "2.8.7"

// removes Central Maven Repository from resolving the dependencies
externalResolvers --= Seq(Resolver.DefaultMavenRepository)

// add Nexus Repository as Resolver for dependencies.
// If Maven Central Repository is required, Nexus takes care on that!
resolvers ++= Seq("Maven-Public" at "http://DN/repository/maven-public/")

// dependency Overrides
dependencyOverrides ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
)

// Ad dependencies
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "com.cmp.fm" %% "fm_2-3-0" % fmVersion,
  "com.kerb4j" % "kerb4j-client" % "0.0.8"
)

// publish to nexus repository
publishTo := {
  val nexus = "http://NEXUS-DN/repository/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "dir4snapshot")
  else
    Some("releases"  at nexus + "dir")
}

// create fat jar while publishing to nexus
artifact in(Compile, assembly) := {
  val art = (artifact in(Compile, assembly)).value
  art.withClassifier(Some("assembly"))
}
addArtifact(artifact in(Compile, assembly), assembly)
*/
