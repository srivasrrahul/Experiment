package StoreService.WebInterface

//import StoreService.APIModel.StoreRepresentation

//import StoreService.APIModel.StoreRepresentation
//import StoreService._


import StoreService.APIModel.{StoreLst, StoreRepresentation}
import StoreService.DomainModel._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

import akka.http.scaladsl.marshalling.{ToResponseMarshaller, ToResponseMarshallable}
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import spray.json.DefaultJsonProtocol
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.Done
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

import spray.json._

import scala.io.StdIn





import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._


object Utils {
  val host = "http://localhost:8080/"
  val versionInuse = "v1/"
  val uriBase = host + versionInuse + "store-service/"
  def convertToRepr(store: Store) : StoreRepresentation = {
    var slug = ""
    store.slug match {
      case Some(x) => slug = x
    }

    var cashBack : Long = 0
    store.cashBack match {
      case Some(x) => cashBack = x
    }

    var image : String = ""
    store.image match {
      case Some(x) => image = x
    }
    new StoreRepresentation(store.id,store.name,store.description,slug,cashBack,image)
  }

  def convertToStore(store: StoreRepresentation) : Store = {

    val newStore = new Store(store.id,store.name,store.description)
    newStore.slug = Some(store.slug)
    newStore.cashBack = Some(store.cashback)
    newStore.image = Some(store.image)
    newStore
  }
}

object WebServer {



  import StoreService.APIModel.ServiceJsonProtoocol._



  implicit val executionContext :ExecutionContext = ExecutionContext.global




  def main(args: Array[String]) {

    // needed to run the route
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future map/flatmap in the end
    implicit val executionContext = system.dispatcher

    //ONLY STUB CODE for testing routing.
    val route : Route =
      (get & path("v1" / "store-service" )) {
        complete(new StoreRepresentation(10,"","","",20,""))
       } ~
        (get & path("v1" / "store-service" / IntNumber)) { id =>
          complete(new StoreRepresentation(10,"","","",20,""))
        } ~
        (patch & path("v1" / "store-service") & entity(as[StoreRepresentation])) { store =>
          complete(new StoreRepresentation(10,"","","",20,""))
        } ~
        (delete & path("v1" / "store-service" / IntNumber)) { id =>
          complete(new StoreRepresentation(10,"","","",20,""))
        } ~
        (post & path("v1" / "store-service" / "create-id")) { complete(StatusCodes.Accepted)
        } ~
        (post & path("v1" / "store-service") & entity(as[StoreRepresentation])) {store => {
          //complete(new StoreRepresentation(10,"","","",20,""))
          val createdStore = new StoreCreateService(Utils.convertToStore(store)).create()
          val representation = Utils.convertToRepr(createdStore)
          complete(representation)
        }}



//      get {
//        pathPrefix("v1" / "store-service") {
//
//        } ~
//          pathPrefix("v1" / "store-service" / IntNumber) { id =>
//            println(id)
//            complete(new GetStoreDetails(id).getData().get)
//
//          }
//      } ~
//       post {
//          path("v1" / "store-service") {
//            complete(new GetStoreDetails(10).getData().get)
//          }
//        }




//    val route: Route =
//      get {
//        pathPrefix("store-service" / IntNumber) { id =>
//          println(id)
//          complete(new GetStoreDetails(id).getData().get)
//
//        }
//      }
    //        post {
    //          path("create-order") {
    //            entity(as[Order]) { order =>
    //              val saved: Future[Done] = saveOrder(order)
    //              onComplete(saved) { done =>
    //                complete("order created")
    //              }
    //            }
    //          }
    //        }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ ⇒ system.terminate()) // and shutdown when done

  }
}
