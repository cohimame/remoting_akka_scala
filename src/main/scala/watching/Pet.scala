package watching

import akka.actor._
import akka.pattern.ask
import akka.pattern.pipe
import akka.util.Timeout
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt
import scala.concurrent.Future
import scala.concurrent.Await
import scala.util.{ Success, Failure }

import com.typesafe.config.ConfigFactory

object Pet extends App {

  val system = ActorSystem(
    "petsystem",ConfigFactory.load.getConfig("workersystem"))
 
  import system.dispatcher
 
  val pet = system.actorOf(Props[EchoActor], name ="pet")
  

  system.scheduler.scheduleOnce(25 seconds){
    system.shutdown()
  }
}