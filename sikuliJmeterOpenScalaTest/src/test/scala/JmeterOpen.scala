import org.scalatest._
import java.awt._
import java.io._
import javax.imageio._
import org.sikuli.basics.Settings
import org.sikuli.script._

/**
 * Created by smakhetov on 06.10.2015.
 */


class JmeterOpen extends FreeSpec with Matchers with BeforeAndAfterEach with CancelAfterFailure{
  var fl = true
  val s = new Screen()

//  println(org.sikuli.basics.Settings.OcrTextSearch)
//  org.sikuli.basics.Settings.OcrTextSearch = true
//  println(org.sikuli.basics.Settings.OcrTextSearch)

 // override def afterEach() {println("After");createScreenCaptureToReport()}

  override def withFixture(test: NoArgTest) = {

    super.withFixture(test) match {
      case failed: Failed =>
        createScreenCaptureToReport()
        failed
      case other =>
        other
    }
  }

  def createScreenCaptureToReport (fileName: String): Unit = {
    val screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
    val capture = new Robot().createScreenCapture(screenRect)
    ImageIO.write(capture, "png", new File("outFiles/" + fileName))
    markup("<div class='spoiler'><input type='checkbox' /><label>Show image</label><div class='box'><img src='../outFiles/" + fileName + "' width='100%' /></div></div>") //height='540'
  }

  def createScreenCaptureToReport (): Unit = {createScreenCaptureToReport(System.currentTimeMillis + ".png")}

  "Open folder" in {
    s.keyDown(Key.WIN)
    s.`type`("e")
    s.keyUp(Key.WIN)
    s.`type`(new Pattern("images/1443792772457.png").similar(0.90f).targetOffset(60, -3), "U:\\testData\\P.MM.02\\by" + Key.ENTER)
    s.wait("images/1443789852119.png",10)
    createScreenCaptureToReport()
    //markup("<img src='../outFiles/0.png' />")
  }

  "Open Jmeter" in {
    s.doubleClick(new Pattern("images/1443789852119.png").targetOffset(-96,-1))
    s.wait("images/1443789892335.png",30)
    s.keyDown(Key.WIN)
    s.`type`(Key.UP)
    s.keyUp(Key.WIN)
    createScreenCaptureToReport()

  }
  "Open and start test" in {
    s.click("images/1443790509505.png")
    s.click("images/1443790525108.png")
    s.`type`("images/1443790760329.png", "U:\\testData\\P.MM.02\\by\\P.MM.02.MSG.005.jmx")
    s.click("images/1443790795300.png")
    s.click("images/1443790679899.png")
    s.click("images/1443790691825.png")
    createScreenCaptureToReport()
  }
"Open results" in {
    s.wait(new Pattern("images/1443791131289.png").exact(),30)
    s.click(new Pattern("images/1443791150291.png").exact())
    s.click("images/1443791408722.png")
    createScreenCaptureToReport()
  }

}
