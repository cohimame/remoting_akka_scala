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

object Watching extends App {
  val system = ActorSystem("watcherSystem")
  import system.dispatcher

  val watcher = system.actorFor(Props[Watcher], name ="watcher")
 
  val remotePet = system.actorFor("akka://echoSystem/user/pet")

 
  watcher ! Watch(remotePet)

  

  system.scheduler.scheduleOnce(15 seconds){
    system.shutdown()
  }
}