package watching

import akka.actor._
import akka.pattern.{ask,pipe}
import akka.util.Timeout
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Future, Await}
import scala.util.{ Success, Failure }

import com.typesafe.config.ConfigFactory

object Supervising extends App {
  val system = ActorSystem(
    "system",ConfigFactory.load.getConfig("watchersystem"))
  import system.dispatcher

  val illActor = system.actorOf(Props[ExceptionThrower])
 
  illActor ! ThrowUp
 
  system.scheduler.scheduleOnce(5 seconds){ illActor ! Heal }

  system.scheduler.scheduleOnce(15 seconds){
    system.shutdown()
  }
}