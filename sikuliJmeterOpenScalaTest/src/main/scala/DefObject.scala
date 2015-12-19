import org.sikuli.basics.Settings
import org.sikuli.script._

/**
 * Created by smakhetov on 06.10.2015.
 */
object DefObject extends scala.App{

    Settings.OcrTextSearch = true
    Settings.OcrTextRead = true

    //val reg = new Region(49,291,37,21)
    val reg = new Region(48,523,159,17)
    //val reg = new Region(26,457,109,21)
    println("[" + reg.text() + "]")

}
