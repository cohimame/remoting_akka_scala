package pingpong

import actorcore._
import com.typesafe.config.ConfigFactory
import akka.actor.{ActorSystem, Props, Actor}
import scala.concurrent.duration._


object Master extends App {

   val system = ActorSystem("pingersystem", 
    ConfigFactory.load.getConfig("pinger-remote"))

   import system.dispatcher

   val future = system.actorSelection(
    "akka://remote@localhost:2552/user/ponger")//.resolveOne
/*
   future.map(aRef => aRef ! "hola!").recover{
     case ActorNotFound(selection) => println("worker not available") 
   } 
*/
   system.scheduler.scheduleOnce(6 seconds){system.shutdown()}

}

/*
object PingerApp extends App {
  val system = ActorSystem("pingersystem", 
    ConfigFactory.load.getConfig("pinger-remote"))

  import system.dispatcher
  
  val pingerActor = system.actorOf(Props[Azaza], "pinger")

  val pongerActor = system.actorFor("akka://pongersystem@localhost:2552/user/ponger")
  
  println("pinger_path=" + pingerActor.path)
  println("remote_ponger_path=" + pongerActor.path)

  system.scheduler.scheduleOnce( 15 seconds ){ 
    pongerActor.tell(actorcore.Msg("bang!"), pingerActor)
  }

  //val inbox = Inbox.create(system) 
  println(
    system.settings.config.getString(
      "pinger-remote.ponger.ponger-system")
    )



  system.scheduler.scheduleOnce( 16 seconds ){ system.shutdown() }

}

*/

/*
import scala.concurrent.Future
import akka.actor.{ActorSystem, Props, Actor}
import akka.remote.

import com.typesafe.config.ConfigFactory
val system = ActorSystem("pingersystem", ConfigFactory.load.getConfig("pinger-remote"))
import system.dispatcher



val maybeActor = system.actorFor("akka://remote@localhost:2552/user/ponger")

val future = Future( maybeActor ! "yo").recover{
  //case RemoteClientError(cause,remote,address) => println("lol:")
  case x:Exception => println("lol")
}

*/