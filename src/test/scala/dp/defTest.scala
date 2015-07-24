package dp

/**
 * Created by smakhetov on 20.07.2015.
 */


import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{FreeSpec, FunSuite, Matchers, FlatSpec}
import org.scalatest.selenium.{Firefox, HtmlUnit, WebBrowser}




class defTestFirefox extends FreeSpec with Matchers with WebBrowser {
  implicit val webDriver: WebDriver = new FirefoxDriver()
  //implicit  val webDriver: WebDriver = new HtmlUnitDriver()
  val host = "https://rseds.eurasiancommission.org/ru/#/"

  "Заголовок главной страницы должен быть корректным" in {
    go to (host + "registry-main")
    pageTitle should be("Реестр структур электронных документов и сведений")
  }
  "Заголовок страницы, на которую делается переход должен быть корректным" in {
    // click on xpath("//li[2]/a") - так тоже можно
    click on partialLinkText("Реестр")
    pageTitle should be("Реестр структур электронных документов и сведений")
  }
  "НА странице должен быть корректный текст" in {
    find(xpath("//*[@id=\"footer\"]/div[1]/div/div[1]/div[1]/ul/li[2]/a")).get.text should be("Реестр структур ЭД")
    find(xpath("//*[@id=\"ng-app\"]/body/span/div/div[1]/data-ng-include[2]/div/div/div/div/ul/li[1]/span/strong")).get.text should be("Структуры ЭД")
  }
  "Закрытие браузера" in {
    quit()
  }
}

