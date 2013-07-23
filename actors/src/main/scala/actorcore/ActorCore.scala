package actorcore

import akka.actor.Actor

case class Ping(n: Int) 
case class Pong(n: Int)  

class Ponger extends Actor {
  def receive = {
    case Ping(k:Int) => 
      { 
        println("Pong("+ k+1 +")")
        sender ! Pong(k+1)
      }
  }
}

class Pinger extends Actor {
  def receive = {
    case Pong(k:Int) =>
      { 
        println("Ping("+ k+1 +")") 
        sender ! Ping(k+1)
      }
  }

}
