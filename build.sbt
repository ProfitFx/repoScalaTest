name := "repoScalaTest"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0-M7" % "test->*"

libraryDependencies += "com.typesafe" % "config" % "1.3.0"

libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.46.0"

(testOptions in Test) += Tests.Argument(TestFrameworks.ScalaTest, "-h", "report")

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4"

libraryDependencies += "cglib" % "cglib-nodep" % "2.2.2"