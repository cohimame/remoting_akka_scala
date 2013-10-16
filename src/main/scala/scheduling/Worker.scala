package scheduling

import akka.actor._
import akka.pattern._
import akka.util._
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._
import scala.concurrent._
import scala.util._
import scala.language.postfixOps

import actorcore.Messages._
import actorcore.{ DumbWorker, RemoteWorker }


object WorkerApp extends App {

  val system = ActorSystem(
    "workersystem",ConfigFactory.load.getConfig("workersystem"))
  import system.dispatcher

  val worker = system.actorOf(Props[RemoteWorker],"remoteworker")

  val dumbWorker = system.actorOf(Props[DumbWorker],"dumbworker")
  
  system.scheduler.scheduleOnce(15 seconds){system.shutdown()}
}