package actorcore
import akka.actor.Actor

case class Msg(m: String)

class Clean extends Actor {
  def receive = {
    case msg @ Msg(m) => 
      println(sender)
      sender ! msg 
  }
}


trait Parrot { this:Actor =>

  def receive:Receive = {
    case msg @ Msg(m) => 
      println(sender)
      sender ! msg 
  }
}

//class Extender1 extends Actor with 