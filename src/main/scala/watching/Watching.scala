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

object PetGazing extends App {
  val system = ActorSystem(
    "watchersystem",ConfigFactory.load.getConfig("watchersystem"))
  import system.dispatcher

  val watcher = system.actorOf(Props[Watcher])
 
  val remotePet: ActorRef = system.actorFor(
    "akka://petsystem@localhost:3500/user/pet")

  watcher ! Watch(remotePet)

  system.scheduler.scheduleOnce(5 seconds){ watcher ! Suffocate(remotePet)}

  system.scheduler.scheduleOnce(15 seconds){
    system.shutdown()
  }
}