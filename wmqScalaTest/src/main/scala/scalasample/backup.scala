package scalasample

/**
 * Created by smakhetov on 25.03.2015.
 */
object backup {
  /*val cf: MQQueueConnectionFactory = new MQQueueConnectionFactory
 // Config
   val conf = ConfigFactory.load()
 cf.setHostName(conf.getString("connection.host")) //  cf.setHostName("impop-gw-by.dev.centre-it.com");
 cf.setPort(conf.getInt("connection.port")) //cf.setPort(1414);
 cf.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP)
 cf.setQueueManager(conf.getString("connection.qmname"))  //cf.setQueueManager("BY.IISVVT.QM")
 cf.setChannel(conf.getString("connection.channel"))//cf.setChannel("SYSTEM.DEF.SVRCONN")
 val connection: MQQueueConnection = cf.createQueueConnection(conf.getString("connection.login"), conf.getString("connection.password")).asInstanceOf[MQQueueConnection]
 val session: MQQueueSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE).asInstanceOf[MQQueueSession]
 val queue: MQQueue = session.createQueue(conf.getString("connection.queue")).asInstanceOf[MQQueue]
 val sender: MQQueueSender = session.createSender(queue).asInstanceOf[MQQueueSender]
 val receiver: MQQueueReceiver = session.createReceiver(queue).asInstanceOf[MQQueueReceiver]
 val uniqueNumber: Long = System.currentTimeMillis % 1000
 //val message: JMSTextMessage = session.createTextMessage("SimplePTP " + uniqueNumber).asInstanceOf[JMSTextMessage]
 val message: JMSTextMessage = session.createTextMessage(stfile).asInstanceOf[JMSTextMessage]
 connection.start  // Start the connection
 sender.send(message)
 println("Sent message:\n" + message)
 //val receivedMessage: JMSMessage = receiver.receive(10000).asInstanceOf[JMSMessage]
 //println("\nReceived message:\n" + receivedMessage)
 sender.close
 receiver.close
 session.close
 connection.close

 println("\nSUCCESS\n")*/
}

//class Point(xc: Int, yc: Int) {
// var x: Int = xc
// var y: Int = yc
//
// def move(dx: Int, dy: Int) {
//  x = x + dx
//  y = y + dy
//  println ("Point x location : " + x);
//  println ("Point y location : " + y);
// }
//}