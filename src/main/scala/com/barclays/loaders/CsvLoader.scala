import java.io.{FileNotFoundException, IOException}
import javax.jms.DeliveryMode._
import javax.jms.Session

import com.barclays.utils.JMSUtils.AllImplicits._
import org.apache.activemq.ActiveMQConnectionFactory

import scala.io.Source

object CSVLoader {
  def main(args: Array[String]): Unit = {
    val connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616")
    val connection = connectionFactory.createConnection()

    connection.start()

    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val q = session.queue("titan-graph")

    try {
      for (line <- Source.fromFile(args(0)).getLines()) {
        println(s"Sending $line")
        val prod = q.producer.deliveryMode(PERSISTENT).send(line)
      }
    } catch {
      case ex: FileNotFoundException => println("Couldn't find that file.")
      case ex: IOException => println("Had an IOException trying to read that file")
    }

    session.closeMyConnection()

    println("Finished loading")
    System.exit(0)
  }
}