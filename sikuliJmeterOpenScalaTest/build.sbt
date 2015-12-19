name := "SikuliJmeterOpen"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0-M9" % "test->*"

(testOptions in Test) += Tests.Argument(TestFrameworks.ScalaTest, "-hD", "report", "-Y", "src/test/customstyles.css", "-fW", "report.txt", "-u", "junitOut")

