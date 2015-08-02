name := "jdbcScalaTest"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0-M7" % "test"

//testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h", "report", "-f", "report.txt")

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
