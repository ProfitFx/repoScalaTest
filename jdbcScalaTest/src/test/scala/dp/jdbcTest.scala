package dp

/**
 * Created by root on 7/31/15.
 */

import java.sql.{Connection,DriverManager}
import org.scalatest.FreeSpec
import org.scalatest.Matchers



class testSQl extends FreeSpec with Matchers{

  val testNom = scala.util.Random.nextInt(10000).toString()
  val url = "jdbc:postgresql://172.16.7.15:5432/test"
  val driver = "org.postgresql.Driver"
  val username = "test"
  val password = "1qazxsw2"
  var connection:Connection = _


  "Select " + testNom + " from DB must be unsucsessful" in {

    println("Select")

    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val statement = connection.createStatement
    //val rs = statement.executeQuery("SELECT id, text FROM testtable")
    val rs = statement.executeQuery("SELECT count(*) as cn from testtable where id = " + testNom)
    while (rs.next) {
      val count = rs.getString("cn")
      count should be("0")
      println("count = %s".format(count))
    }

    connection.close
  }


  "Insert to db values " + testNom + " must be sucsessful" in {
      println("Insert")

      try {
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)
        val statement = connection.createStatement.execute("insert into testtable (id, text) values ("+ testNom + ",'" + testNom + "')")

      } catch {
        case e: Exception => e.printStackTrace
      }
      connection.close
    "10123" should be ("11012")
    }


  "Select " + testNom + " from DB must be sucsessful" in {

    println("Select")

      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      //val rs = statement.executeQuery("SELECT id, text FROM testtable")
      val rs = statement.executeQuery("SELECT count(*) as cn from testtable where id = " + testNom)
      while (rs.next) {
        val count = rs.getString("cn")
        count should be("1")
       println("count = %s".format(count))
      }

    connection.close
  }


}








