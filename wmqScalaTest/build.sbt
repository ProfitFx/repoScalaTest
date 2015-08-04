name := "wmqScalaTest"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies += "com.typesafe" % "config" % "1.3.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0-M7" % "test"

//libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0-M7" % "test->*"

//(testOptions in Test) += Tests.Argument(TestFrameworks.ScalaTest, "-h", "report", "-fW", "report.txt")