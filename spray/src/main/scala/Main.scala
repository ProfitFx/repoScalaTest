/**
 * Created by smakhetov on 18.12.2015.
 */

import akka.actor.ActorSystem
import spray.http.HttpHeaders.`Content-Disposition`
import spray.http.MediaTypes
import spray.routing.SimpleRoutingApp

object Main extends App with SimpleRoutingApp {
  implicit val system = ActorSystem("my-system")
  val printer = new scala.xml.PrettyPrinter(80, 2)
  //header Content-Disposition
  startServer(interface = "localhost", port = 8080) {
    path("xml"/JavaUUID) {juuid =>
      get {
        // respondWithHeader(`Content-Disposition`("attachment", Map(("filename", "some.pdf")))){
        respondWithMediaType(MediaTypes.`text/xml`){
          complete {printer.format(<root><ID>123</ID><messageID>{juuid}</messageID></root>)
          }
        }			
        //
		//      path("jpath") {
        //post { entity(as[String]) {
        //  order => complete{println(order);"This is a POST request." + order}}
       // }
     // }
      }
    }
  }
}