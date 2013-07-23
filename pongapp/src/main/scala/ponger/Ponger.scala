package ponger

import actorcore._
import com.typesafe.config.ConfigFactory
import akka.actor.{ActorSystem, Props, Actor}//, Inbox} only in akka2.2 
import scala.concurrent.duration._


object PongerApp extends App {	
	val system = ActorSystem(
		"pongersystem",
		ConfigFactory.load.getConfig("ponger-remote"))
	val pongerActor = system.actorOf(Props[Ponger], "ponger")

	println("ponger_path=" + pongerActor.path)


	//system.shutdown()

}