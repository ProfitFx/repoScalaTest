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

/*class BlogSpec extends FlatSpec with Matchers with HtmlUnit {

  val host = "https://rseds.eurasiancommission.org/ru/#/"

  "Заголовок главной страницы" should "должен быть корректным" in {
    go to (host + "registry-main")
    pageTitle should be ("Реестр структур электронных документов и сведений")
  }

  /*"Заголовок страницы, на которую делается переход" should "должен быть корректным" in {
    click on linkText("Реестр")
    pageTitle should be("Реестр структур электронных документов и сведений")
  }*/
}*/


/*class defTest extends FlatSpec with Matchers with WebBrowser {

  implicit val webDriver: WebDriver = new HtmlUnitDriver
  val host = "https://rseds.eurasiancommission.org/ru/#/"

  "Заголовок главной страницы" should "должен быть корректным" in {
    go to (host + "registry-main")
    pageTitle should be ("Реестр структур электронных документов и сведений")
  }
  "Заголовок страницы, на которую делается переход" should "должен быть корректным" in {
   // click on xpath("//li[2]/a")
    click on partialLinkText("Реестр")
    pageTitle should be("Реестр структур электронных документов и сведений")
  }
}*/