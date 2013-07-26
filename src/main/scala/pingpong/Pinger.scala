package pingpong

import actorcore._
import com.typesafe.config.ConfigFactory
import akka.actor.{ActorSystem, Props, Actor}
import scala.concurrent.duration._

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

/*

  //val inbox = Inbox.create(system) 
  println(
    system.settings.config.getString(
      "pinger-remote.ponger.ponger-system")
    )
*/


  system.scheduler.scheduleOnce( 16 seconds ){ system.shutdown() }

}