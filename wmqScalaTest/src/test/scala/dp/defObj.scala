package dp

import java.io._
import java.util.UUID
import javax.jms.Session



import com.ibm.jms.{JMSBytesMessage, JMSMessage, JMSTextMessage}
import com.ibm.mq.jms._

import com.typesafe.config.ConfigFactory

import scala.io.Source

import org.scalatest.time._
import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatest.concurrent.Timeouts

/**
 * Created by smakhetov on 03.08.2015.
 */





class connectionCreate {
  val cf: MQQueueConnectionFactory = new MQQueueConnectionFactory
  val conf = ConfigFactory.load()
  cf.setHostName(conf.getString("connection.host")) //  cf.setHostName("impop-gw-by.dev.centre-it.com");
  cf.setPort(conf.getInt("connection.port")) //cf.setPort(1414);
  cf.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP)
  cf.setQueueManager(conf.getString("connection.qmname"))  //cf.setQueueManager("BY.IISVVT.QM")
  cf.setChannel(conf.getString("connection.channel"))//cf.setChannel("SYSTEM.DEF.SVRCONN")
  val connection: MQQueueConnection = cf.createQueueConnection(conf.getString("connection.login"), conf.getString("connection.password")).asInstanceOf[MQQueueConnection]
  val session: MQQueueSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE).asInstanceOf[MQQueueSession]
  connection.start
}


class connectionClose (connection: MQQueueConnection, session: MQQueueSession) {
  session.close
  connection.close
}

class sender (session: MQQueueSession, qname: String,sendFile: String){
  val queue: MQQueue = session.createQueue(qname).asInstanceOf[MQQueue]
  val sender: MQQueueSender = session.createSender(queue).asInstanceOf[MQQueueSender]
  //val strMessage = scala.io.Source.fromFile(conf.getString("files.send")).mkString //"in.xml"
  var strMessage = ""
  val fileContent = Source.fromFile(sendFile,"UTF-8").getLines()
  fileContent.foreach((line: String) => strMessage = strMessage + line.replaceFirst("\\$\\{__UUID}",UUID.randomUUID.toString) + "\n")
  val message: JMSTextMessage = session.createTextMessage(strMessage).asInstanceOf[JMSTextMessage]
  sender.send(message)
  //println("Sent message:\n" + message)
  println("Send message")
}

class bytesReceiver(session: MQQueueSession, qname: String, receivFile: String) {

  val queue: MQQueue = session.createQueue(qname).asInstanceOf[MQQueue]
  val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]
  val receivedMessage: JMSBytesMessage = receiver.receive(60000).asInstanceOf[JMSBytesMessage]

//  println("\nReceived message:\n" + receivedMessage)
//  println("-------------------------------------------------------------")
//  val recivedText = receivedMessage.getText
//  println(recivedText)
  // FileWriter
  val bw = new BufferedWriter(new FileWriter(new File(receivFile)))
  //val arr: Array
  val mLenght = receivedMessage.getBodyLength.toInt
  var bArray :Array[Byte] = new Array[Byte](mLenght)
  receivedMessage.readBytes(bArray,mLenght)
  val text: String = new String(bArray, "UTF-8")
  bw.write(text)
  println("Received message")
 // bw.write(receivedMessage.getBodyLength.toString)
  bw.close()
}

class textReceiver (session: MQQueueSession, qname: String, receivFile: String) {
  val queue: MQQueue = session.createQueue(qname).asInstanceOf[MQQueue]
  val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]
  val receivedMessage: JMSTextMessage = receiver.receive(60000).asInstanceOf[JMSTextMessage]
  // val receivedMessage: JMSTextMessageMessage = receiver.receive(10000).asInstanceOf[JMSTextMessage]
  println("\nReceived message:\n" + receivedMessage)
  //  println("-------------------------------------------------------------")
  //  val recivedText = receivedMessage.getText
  //  println(recivedText)
  // FileWriter
  val bw = new BufferedWriter(new FileWriter(new File(receivFile)))
  bw.write(receivedMessage.getText)
  bw.close()
}

class queuePurge(session: MQQueueSession, qname: String) {

  val queue: MQQueue = session.createQueue(qname).asInstanceOf[MQQueue]
  val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]

  while(receiver.receive(1000).asInstanceOf[JMSBytesMessage]!= null){
    println("purge message")
  }

//  class def
//  {
//    var length: Int = new Long(message.getBodyLength).intValue
//    var b: Array[Byte] = new Array[Byte](length)
//    Nothing Nothing;
//    var text: String = new String(b, "UTF-8")
//  }

}