package pingpong

import actorcore._
import com.typesafe.config.ConfigFactory
import akka.actor.{ActorSystem, Props, Actor}
import scala.concurrent.duration._


object PongerApp extends App {	
	val system = ActorSystem("pongersystem",
		ConfigFactory.load.getConfig("ponger-remote"))

	import system.dispatcher


	val pongerActor = system.actorOf(Props[Azaza], "ponger")

	println("ponger_path=" + pongerActor.path)



	system.scheduler.scheduleOnce( 20 seconds ){ system.shutdown() }

}