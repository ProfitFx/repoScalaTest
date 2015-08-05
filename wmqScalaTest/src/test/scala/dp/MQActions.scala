package dp

import java.io.{BufferedWriter, File, FileWriter}
import java.util.UUID
import javax.jms.Session

import com.ibm.jms.{JMSBytesMessage, JMSTextMessage}
import com.ibm.mq.jms._
import com.typesafe.config.ConfigFactory

import scala.io.Source

/**
 * Created by smakhetov on 05.08.2015.
 */
class MQActions {
  val conf = ConfigFactory.load()

  // def connectionCreate {
  val cf: MQQueueConnectionFactory = new MQQueueConnectionFactory
  // val conf = ConfigFactory.load()
  cf.setHostName(conf.getString("connection.host")) //  cf.setHostName("impop-gw-by.dev.centre-it.com");
  cf.setPort(conf.getInt("connection.port")) //cf.setPort(1414);
  cf.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP)
  cf.setQueueManager(conf.getString("connection.qmname"))  //cf.setQueueManager("BY.IISVVT.QM")
  cf.setChannel(conf.getString("connection.channel"))//cf.setChannel("SYSTEM.DEF.SVRCONN")

  val connection: MQQueueConnection = cf.createQueueConnection(conf.getString("connection.login"), conf.getString("connection.password")).asInstanceOf[MQQueueConnection]
  val session: MQQueueSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE).asInstanceOf[MQQueueSession]
  connection.start
  //}


  def queuePurge(qName: String)
  {
    val queue: MQQueue = session.createQueue(qName).asInstanceOf[MQQueue]
    val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]
    while (receiver.receive(1000) != null) {
      println("purge message")
    }
  }

  def sender (qName: String, sendFile: String){
    val queue: MQQueue = session.createQueue(qName).asInstanceOf[MQQueue]
    val sender: MQQueueSender = session.createSender(queue).asInstanceOf[MQQueueSender]
    val strMessage = Source.fromFile(sendFile).mkString //"in.xml"
    val message: JMSTextMessage = session.createTextMessage(strMessage).asInstanceOf[JMSTextMessage]
    sender.send(message)
    println("Send message, lenght = " + strMessage.length.toString)
  }

  def sender (qName: String, sendFile: String, saveFile: String, messageID: String){
    val queue: MQQueue = session.createQueue(qName).asInstanceOf[MQQueue]
    val sender: MQQueueSender = session.createSender(queue).asInstanceOf[MQQueueSender]
    var strMessage = ""
    val fileContent = Source.fromFile(sendFile,"UTF-8").getLines()
    fileContent.foreach((line: String) => strMessage = strMessage + line.replaceFirst("<wsa:MessageID>(.*?)</wsa:MessageID>","<wsa:MessageID>" + messageID + "</wsa:MessageID>").replaceFirst("\\$\\{__UUID}",UUID.randomUUID.toString) + "\n")
    val message: JMSTextMessage = session.createTextMessage(strMessage).asInstanceOf[JMSTextMessage]
    sender.send(message)
    println("Send message,    lenght = " + strMessage.length.toString)
    val bw = new BufferedWriter(new FileWriter(new File(saveFile)))
    bw.write(strMessage)
    bw.close()
  }

  def textReceiver (qName: String, receivFile: String) {
    val queue: MQQueue = session.createQueue(qName).asInstanceOf[MQQueue]
    val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]
    val receivedMessage: JMSTextMessage = receiver.receive(60000).asInstanceOf[JMSTextMessage]
    println("Receive message, lenght = " + receivedMessage.getText.length.toString)
    //  println("\nReceived message:\n" + receivedMessage)
    val bw = new BufferedWriter(new FileWriter(new File(receivFile)))
    bw.write(receivedMessage.getText)
    bw.close()
      }

  def bytesReceiver  (qName: String, receivFile: String) :String = {

    val queue: MQQueue = session.createQueue(qName).asInstanceOf[MQQueue]
    val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]
    val receivedMessage: JMSBytesMessage = receiver.receive(60000).asInstanceOf[JMSBytesMessage]
    val bw = new BufferedWriter(new FileWriter(new File(receivFile)))
    val mLenght = receivedMessage.getBodyLength.toInt
    var bArray :Array[Byte] = new Array[Byte](mLenght)
    receivedMessage.readBytes(bArray,mLenght)
    val text: String = new String(bArray, "UTF-8")
    bw.write(text)
    println("Receive message, lenght = " + receivedMessage.getBodyLength.toString)
    // bw.write(receivedMessage.getBodyLength.toString)
    bw.close()
    return text
  }

  def connectionClose {
    session.close
    connection.close
  }
}
