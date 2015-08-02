package dp

/**
 * Created by smakhetov on 23.07.2015.
*/




import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.time._
import org.scalatest.{FreeSpec, FunSuite, Matchers, FlatSpec}
import org.scalatest.selenium.{Firefox, HtmlUnit, WebBrowser}
import org.scalatest.concurrent.Eventually
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory




class reestrTests  extends FreeSpec with Matchers with WebBrowser with Eventually {

  val conf = ConfigFactory.load()
  val timeLimit = (Span(2000, Millis))
  
  // failAfter(10 millis){pageTitle should be ("Модель общих процессов")} - fail after 10 millis

  implicit override val patienceConfig =
    PatienceConfig(timeout = scaled(Span(10, Seconds)), interval = scaled(Span(5, Millis)))

  implicit val webDriver: WebDriver = new FirefoxDriver()
  //implicit  val webDriver: WebDriver = new HtmlUnitDriver()
  val baseUrl = conf.getString("test.baseUrl")  //"https://eomi.eaeunion.org/ru/#/"

  "Заголовок главной страницы должен быть корректным" in {
    go to (baseUrl)
    pageTitle should be ("Модель общих процессов")
  }
  "Заголовок страницы, на которую делается переход должен быть корректным" in {
    // click on xpath("//li[2]/a") - так тоже можно
    click on partialLinkText("Области охвата")
    pageTitle should be ("Модель общих процессов")
  }
  "На странице \"Области охвата\" должен быть корректный текст" in {
    eventually{find(xpath("//*[@id=\"ng-app\"]/body/div[1]/div[1]/data-ng-include[1]/div/div/h1")).get.text should be("Области охвата")}
  }
  "На странице \"Модель\" должен быть корректный текст" in {
    click on partialLinkText("Модель")
    eventually{find(xpath("//*[@id=\"ng-app\"]/body/div[1]/data-ng-include/div/div/div/div/ul/li[1]/span/strong")).get.text should be("Модель")}
  }
  "На странице \"Интерактивная модель\" должен быть корректный текст" in {
    click on partialLinkText("Интерактивная модель")
    eventually{find(xpath("//*[@id=\"ng-app\"]/body/div[1]/data-ng-include/div/div/div/div/ul/li[2]/span/strong")).get.text should be("Интерактивная модель")}
  }
  "На странице \"Репозиторий\" должен быть корректный текст" in {
    click on partialLinkText("Репозиторий")
    eventually{find(xpath("//*[@id=\"ng-app\"]/body/div[1]/data-ng-include/div/div/div/div/ul/li[3]/span/strong")).get.text should be("Репозиторий")}
  }
  "Закрытие браузера" in {
    quit()
  }
}
