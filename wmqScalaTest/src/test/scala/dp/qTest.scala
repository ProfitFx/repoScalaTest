package dp

/**
 * Created by smakhetov on 05.08.2015.
 */

import java.util.UUID

import com.typesafe.config.ConfigFactory
import org.scalatest.{FreeSpec, Matchers}

class qTest extends FreeSpec with Matchers{

  //Insert variables from /src/test/recources/application.conf
  val conf = ConfigFactory.load()
  val queueSend = conf.getString("connection.queueSend")
  val queueReceive = conf.getString("connection.queueReceive")
  val fileSend = conf.getString("files.send")
  val fileSave = conf.getString("files.save")
  val fileReceive_1 = conf.getString("files.receive_1")
  val fileReceive_2 = conf.getString("files.receive_2")


  val messageID = "urn:uuid:" + UUID.randomUUID.toString

  //create connection
  val mq = new MQActions

  "Purge queue" in {
    mq.queuePurge(queueReceive)
  }

  "Send message with messageID = " + messageID in {
    mq.sender(queueSend, fileSend, fileSave, messageID)
  }

  "Recieve message of delivery" in {
    mq.bytesReceiver(queueReceive, fileReceive_1) should include (messageID)
  }

  "Recieve message with dateTime" in {
    mq.bytesReceiver(queueReceive, fileReceive_2) should include ("<csdo:UpdateDateTime>2015-07-31T22:24:08Z</csdo:UpdateDateTime>")
  }

  "CloseConn" in {
    mq.connectionClose
  }

}
