package dp

import com.ibm.mq.jms.{MQQueueSession, MQQueueConnection}
import com.typesafe.config.ConfigFactory
import org.scalatest.{Matchers, FreeSpec}


/**
 * Created by smakhetov on 04.08.2015.
 */




  class queueTest extends FreeSpec with Matchers{


  // Надо разобраться с Fixture
  private def withFixture(testFunction: (MQQueueConnection, MQQueueSession) => Unit) {

    // Create needed mutable objects
    var sb : MQQueueConnection = null
    val lb : MQQueueSession = null
    // Invoke the test function, passing in the mutable objects
    testFunction(sb, lb)
  }

    val conf = ConfigFactory.load()

    "Проверка доступности менеджера сообщений" in  {
      val copen = new connectionCreate
      val close = new connectionClose(copen.connection,copen.session)
    }

    "Чистка очереди" in {
      val copen = new connectionCreate
      val purge = new queuePurge(copen.session, conf.getString("connection.queueReceive"))
      val close = new connectionClose(copen.connection,copen.session)
    }
    "Отправка сообщения" in {
      val copen = new connectionCreate
      val send = new sender(copen.session, conf.getString("connection.queueSend"), conf.getString("files.send"))
      val close = new connectionClose(copen.connection,copen.session)
    }
    "Прием сообщения о принятии в работу" in {
      val copen = new connectionCreate
      val receiver1 = new bytesReceiver(copen.session, conf.getString("connection.queueReceive"), conf.getString("files.receive1"))
     // val receiver2 = new bytesReceiver(copen.session, conf.getString("connection.queueReceive"), conf.getString("files.receive2"))
      val close = new connectionClose(copen.connection,copen.session)
    }
  "Прием сообщения с датой временем обновления реестра" in {
    val copen = new connectionCreate
    //val receiver1 = new bytesReceiver(copen.session, conf.getString("connection.queueReceive"), conf.getString("files.receive1"))
    val receiver2 = new bytesReceiver(copen.session, conf.getString("connection.queueReceive"), conf.getString("files.receive2"))
    val close = new connectionClose(copen.connection,copen.session)
  }



}
