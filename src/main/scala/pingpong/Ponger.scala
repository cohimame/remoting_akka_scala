package pingpong

import actorcore._
import com.typesafe.config.ConfigFactory
import akka.actor.{ActorSystem, Props, Actor}
import scala.concurrent.duration._

/*

object RemoteWorker extends App {
  val system = ActorSystem(
    "remote",ConfigFactory.load.getConfig("ponger-remote"))

  import system.dispatcher

  val worker = system.actorOf(Props[Clean], "worker")

  println("my path is: " + worker.path)

  system.scheduler.scheduleOnce( 200 seconds ){ system.shutdown() }
}



object PongerApp extends App {	
	val system = ActorSystem("pongersystem",
		ConfigFactory.load.getConfig("ponger-remote"))

	import system.dispatcher


	val pongerActor = system.actorOf(Props[Azaza], "ponger")

	println("ponger_path=" + pongerActor.path)



	system.scheduler.scheduleOnce( 20 seconds ){ system.shutdown() }

}

*/