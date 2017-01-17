package ProductService.AsyncLayer


import java.util.concurrent.CompletionStage

import akka.stream._
import akka.stream.scaladsl.Flow
import scala.concurrent._

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.{ NotUsed, Done }
import akka.actor.ActorSystem
import akka.util.ByteString
import scala.concurrent._
import scala.concurrent.duration._
import java.nio.file.Paths
import akka.http.scaladsl.Http
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

import scala.concurrent.Future
import scala.util.{Success, Failure}

import akka.stream.scaladsl.Tcp.OutgoingConnection


/**
 * Created by rasrivastava on 1/17/17.
 */
case class ServiceRegisterMessage(val name : String,val ip: String,val port : Int) {

}
class AsyncRegister extends Actor {
  implicit def actorSystem: ActorSystem
  implicit def materializer : Materializer
  implicit def executionContext : ExecutionContext
  def receive = {





  }


}

object AsyncPostTest extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val uniqueIdActor = system.actorOf(Props[AsyncRegister],name = "testActor")
  println(uniqueIdActor ! new ServiceRegisterMessage("test","test",10))

}
