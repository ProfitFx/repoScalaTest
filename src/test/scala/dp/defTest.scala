package dp

/**
 * Created by smakhetov on 20.07.2015.
 */


import org.easymock.internal.matchers.Find
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.internal.FindsByXPath
import org.scalatest.{FreeSpec, FunSuite, Matchers, FlatSpec}
import org.scalatest.selenium.{Firefox, HtmlUnit, WebBrowser}




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

class defTestFirefox extends FreeSpec with Matchers with WebBrowser {

  //implicit val webDriver: WebDriver = new FirefoxDriver()
  implicit  val webDriver: WebDriver = new HtmlUnitDriver()
  val host = "https://rseds.eurasiancommission.org/ru/#/"

  "Заголовок главной страницы должен быть корректным" in {
    go to (host + "registry-main")
    pageTitle should be ("Реестр структур электронных документов и сведений")
  }
  "Заголовок страницы, на которую делается переход должен быть корректным" in {
    // click on xpath("//li[2]/a") - так тоже можно
    click on partialLinkText("Реестр")
    pageTitle should be("Реестр структур электронных документов и сведений")
    //textArea("portal").value should be ("123")
  }
  "НА странице должен быть корректный текст" in {
    // click on xpath("//li[2]/a") - так тоже можно
    textArea("portal").value should be ("123")
    //textArea(FindsByXPath("//li[2]/a")).value
   // find(cssSelector("css=li.ng-binding")) should be ("Реестр")
  }
}

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