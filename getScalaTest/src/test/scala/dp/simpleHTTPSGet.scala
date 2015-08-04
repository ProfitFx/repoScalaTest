package dp

/**
 * Created by smakhetov on 30.07.2015.
 */

import java.io.File
import java.net.URL

import org.scalatest.{FreeSpec, Matchers}

import scala.io.Source
import scala.sys.process._


class simpleHTTPSGet extends FreeSpec with Matchers {
  def get(url: String) = Source.fromURL(url, "UTF-8").mkString

  val addr = "https://rseds.eurasiancommission.org/ru/#/registry/support"
  //val vadrr = "https://rseds.eurasiancommission.org/video/video2.mp4"


  "Get запрос к сайту должен содержать корректный текст " in {
    get(addr) should include ("Реестр структур электронных документов и сведений")
  }
  for ( a <- 1 to 4 ) {
  "Запрос видео video" + a.toString + ".mp4  должен пройти успешно"  in {
    val X = new URL("https://rseds.eurasiancommission.org/video/video" + a.toString + ".mp4") #> new File("video" + a.toString + ".mp4") !!
    }
  }
  for ( a <- 1 to 4 ) {
    "Повторный 3апрос видео video" + a.toString + ".mp4  должен пройти успешно"  in {
      val X = new URL("https://rseds.eurasiancommission.org/video/video" + a.toString + ".mp4") #> new File("video_" + a.toString + ".mp4") !!
    }
  }



}
