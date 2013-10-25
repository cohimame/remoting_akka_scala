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


case class Echo(m: String)
case class DelayedEcho(m: String)
case object Die

case class Watch(pet: ActorRef)
case class Suffocate(pet: ActorRef)

class EchoActor extends Actor {
  def receive = {
    
    case msg @ Echo(m) => sender ! msg

    case DelayedEcho(m) =>
      Thread.sleep(2000)
      sender ! Echo(m)

    case Die => 
      println(s"gotta Die")
      self ! PoisonPill

  }
}

class Watcher extends Actor {
  def receive = {

    case Watch(pet) => context.watch(pet)

    case Suffocate(pet) => 
      println(s"gotta suffocate $pet")
      pet ! Die

    case Terminated(dunno) => println("this guy is dead: " + dunno)
  }
}