package dp

/**
 * Created by smakhetov on 24.07.2015.
 */


import java.util.UUID

import org.scalatest.{FreeSpec, FunSuite, Matchers, FlatSpec, Tag}

object SlowTest extends Tag("com.mycompany.tags.SlowTest")



class RegToFdaTest extends FreeSpec with Matchers {
  "Сообщение  messageID = " + UUID.randomUUID().toString() +  " отправлено в сервис уровня Reg" in { assert(1==1) }
  "Сервис уровня Reg принял сообщение и зафиксировал это в логах" taggedAs (SlowTest) in {}
  "В БД сервиса уровня Reg появилась запись" in {}
  "В директории хранения сообщений появился файл с сообщением" in { assert(1==2)}
  "Сообщение отправлено в очередь" in {}
  "Сообщение смаршрутизировано во входящую очередь менеджера сообщений уровня Fda" in {}
  "Сервис уровня Fda принял сообщение и зафиксировал это в логах" in {}
  "В БД сервиса уровня Fda появилась запись" in {}
  "Сообщение смаршрутизировано получателю" in {}
}

class FdaToRegTest extends FreeSpec with Matchers {
  "Сообщение  messageID = " + UUID.randomUUID().toString() +  " отправлено в сервис уровня Reg" in { assert(1==1) }
  "Сервис уровня Reg принял сообщение и зафиксировал это в логах" in { }
  "В БД сервиса уровня Reg появилась запись" in {}
  "В директории хранения сообщений появился файл с сообщением" in { assert(1==1)}
  "Сообщение отправлено в очередь"  in {}
  "Сообщение смаршрутизировано во входящую очередь менеджера сообщений уровня Fda" in {}
  "Сервис уровня Fda принял сообщение и зафиксировал это в логах" in {}
  "В БД сервиса уровня Fda появилась запись" ignore {}
  "Сообщение смаршрутизировано получателю" ignore {}
}


