package scalasample

/**
 * Created by smakhetov on 19.03.2015.
 */

import com.sun.org.apache.bcel.internal.generic.RETURN
import com.typesafe.config.ConfigFactory



import javax.jms.JMSException
import javax.jms.Session
import com.ibm.jms.JMSMessage
import com.ibm.jms.JMSTextMessage
import com.ibm.mq.jms.JMSC
import com.ibm.mq.jms.MQQueue
import com.ibm.mq.jms.MQQueueConnection
import com.ibm.mq.jms.MQQueueConnectionFactory
import com.ibm.mq.jms.MQQueueReceiver
import com.ibm.mq.jms.MQQueueSender
import com.ibm.mq.jms.MQQueueSession
import scala.io.Source


object sugar {
  def fts(pth: String): String = {
    return Source.fromFile(pth).mkString
  }
  val filecont = (path: String) => Source.fromFile(path).mkString
}

object operator {


  def conncreate: MQQueueConnection  = {
    val cf: MQQueueConnectionFactory = new MQQueueConnectionFactory
    // Config
    val conf = ConfigFactory.load()
    cf.setHostName(conf.getString("connection.host"))
    cf.setPort(conf.getInt("connection.port"))
    cf.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP)
    cf.setQueueManager(conf.getString("connection.qmname"))
    cf.setChannel(conf.getString("connection.channel"))
    val connection: MQQueueConnection = cf.createQueueConnection(conf.getString("connection.login"), conf.getString("connection.password")).asInstanceOf[MQQueueConnection]
    connection.start
    return connection
  }

  def connclose(connection:MQQueueConnection,session:MQQueueSession) {
    session.close()
    connection.close()
  }

  def send: Unit ={
    val conf = ConfigFactory.load()
    val connection = conncreate
    val session: MQQueueSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE).asInstanceOf[MQQueueSession]
    val queue: MQQueue = session.createQueue(conf.getString("connection.queueTo")).asInstanceOf[MQQueue]
    val sender: MQQueueSender = session.createSender(queue).asInstanceOf[MQQueueSender]
    val uniqueNumber: Long = System.currentTimeMillis % 1000
    val message: JMSTextMessage = session.createTextMessage("SimplePTP " + uniqueNumber).asInstanceOf[JMSTextMessage]
    //val message: JMSTextMessage = session.createTextMessage(sugar.filecont("1.txt")).asInstanceOf[JMSTextMessage]
    //connection.start
    sender.send(message)
    println("Sent message:\n" + message)
    sender.close
    connclose(connection,session)
  }

  def recieve:Unit={
    val conf = ConfigFactory.load()
    val connection = conncreate
    val session: MQQueueSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE).asInstanceOf[MQQueueSession]
    val queue: MQQueue = session.createQueue(conf.getString("connection.queueFrom")).asInstanceOf[MQQueue]
    val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]
  //  connection.start
    val receivedMessage: JMSMessage = receiver.receive(10000).asInstanceOf[JMSMessage]
    receiver.close
    connclose(connection,session)
    println("\nReceived message:\n" + receivedMessage)
  }
}

object MQWriteRead extends App{



  operator.send
  operator.recieve

}

