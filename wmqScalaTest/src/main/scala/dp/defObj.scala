package dp

import java.io._
import java.net.URL
import java.nio.file.Path
import java.util.UUID
import javax.jms.Session



import com.ibm.jms.{JMSMessage, JMSTextMessage}
import com.ibm.mq.jms._

import com.typesafe.config.ConfigFactory

import scala.io.Source

/**
 * Created by smakhetov on 03.08.2015.
 */

object testApp extends App{

  var message = ""
  val fileContent = Source.fromFile("in.xml","UTF-8").getLines
  fileContent.foreach((line: String) => message = message + line.replaceFirst("\\$\\{__UUID}",UUID.randomUUID.toString) + "\n")
  println(message)
}

object defObj extends App {

  val conf = ConfigFactory.load()

  val copen = new connectionCreate
  val connection = copen.connection
  val session = copen.session

  val purge = new queuePurge(session,"queue:///Q1")
 // val send = new sender(session,"queue:///Q1",conf.getString("files.send"))
 // val recive = new reciever(session,"queue:///Q1",conf.getString("files.receive"))

  val close = new connectionClose(connection,session)
  }

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
  println("Sent message:\n" + message)
}

class reciever (session: MQQueueSession, qname: String, receivFile: String) {
  val queue: MQQueue = session.createQueue(qname).asInstanceOf[MQQueue]
  val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]
  val receivedMessage: JMSTextMessage = receiver.receive(10000).asInstanceOf[JMSTextMessage]
 // val receivedMessage: JMSTextMessageMessage = receiver.receive(10000).asInstanceOf[JMSTextMessage]
  println("\nReceived message:\n" + receivedMessage)
  println("-------------------------------------------------------------")
  val recivedText = receivedMessage.getText
  println(recivedText)
  // FileWriter
  val bw = new BufferedWriter(new FileWriter(new File(receivFile)))
  bw.write(recivedText)
  bw.close()
}

class queuePurge(session: MQQueueSession, qname: String) {

  val queue: MQQueue = session.createQueue(qname).asInstanceOf[MQQueue]
  val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]

  while(receiver.receive(1000).asInstanceOf[JMSTextMessage]!= null){
    println("purge message")
  }



//  while(!queue.isEmpty){
//    receiver.receive(1000)
//    println(queue.isEmpty)
//  }
}