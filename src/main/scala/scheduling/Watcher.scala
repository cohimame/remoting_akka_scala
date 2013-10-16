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

/*
  1. Existing remote actor 
  2. Absent actor in existing remote system
  3. Not existing system
*/

object WatchApp extends App {

  val system = ActorSystem(
    "watchersystem",ConfigFactory.load.getConfig("watchersystem"))
  import system.dispatcher



           /* 1. Existing remote actors 
  */
  val remoteWorker = system.actorFor(
    "akka://workersystem@localhost:3500/user/remoteworker")
  
  val remoteDumbWorker = system.actorFor(
    "akka://workersystem@localhost:3500/user/dumbworker")
  
  
  val f: Future[Any] = remoteWorker.ask(Work())(Timeout(4 second)) 

  f.andThen {
    case Success(m) => println("this is "+ m)
    case Failure(exception) => println("suddenly:" + exception)
  }  

  remoteDumbWorker.ask(Work)(Timeout(4 second)).andThen {
    case Success(m) => println("this is "+ m)
    case Failure(exception) => println("suddenly:" + exception)
  } 


          /* 2. Absent actor in existing remote system
  */
  

  val unexistingRemoteActor = system.actorFor(
    "akka://workersystem@localhost:3500/user/unexisting")          

  val future = unexistingRemoteActor.ask(Work())(Timeout(4 second))

  future.andThen {
    case Success(m) => println("this is "+ m)
    case Failure(exception) => println("suddenly:" + exception)
  } 

  /* 3. Not existing system
  */

  val fakeActorInFakeSystem = system.actorFor(
    "akka://fake@localhost:3500/user/unexisting")          

  val futureFake = fakeActorInFakeSystem.ask(Work())(Timeout(4 second))

  futureFake.andThen {
    case Success(m) => println("this is "+ m)
    case Failure(exception) => println("suddenly:" + exception)
  } 

  system.scheduler.scheduleOnce(6 seconds){system.shutdown()}
}