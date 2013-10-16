package actorcore
import akka.actor.Actor


object Messages{
  case class Work()
  case class Done()
}



class RemoteWorker extends Actor {
  import Messages._
  def receive = {
    case Work() =>
      println("got task from sender: " + sender)
      sender ! Done
  }
}

class DumbWorker extends Actor {
  import Messages._
  def receive = {
    case Work() =>
      println("who is " + sender +" ????")
  }
}