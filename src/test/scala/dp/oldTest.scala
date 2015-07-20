package dp

/**
 * Created by smakhetov on 20.07.2015.
 */

import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.selenium.{HtmlUnit, WebBrowser}

/*class oldTest extends FlatSpec with Matchers with WebBrowser {

    implicit val webDriver: WebDriver = new HtmlUnitDriver
    val host = "http://www.scalatest.org/user_guide/using_selenium"

    "The blog app home page" should "have the correct title" in {
      go to (host)
      pageTitle should be ("ScalaTest")
    }
}*/