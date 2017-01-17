package ProductService.WebInterface

import ProductService.APIModel.ProductRepresentation
import ProductService.AsyncLayer._
import akka.stream.ActorMaterializer
import akka.actor.{Props, ActorSystem}

import scala.concurrent.ExecutionContext


import scala.util.{Success, Failure}

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

import akka.http.scaladsl.marshalling.{ToResponseMarshaller, ToResponseMarshallable}
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import spray.json.DefaultJsonProtocol
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

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
import spray.json.DefaultJsonProtocol

import spray.json._


/**
 * Created by rasrivastava on 1/17/17.
 */
object WebServer {



  import ProductService.APIModel.ProductRepresentationJsonProtocol._



  implicit val executionContext :ExecutionContext = ExecutionContext.global
  implicit val system = ActorSystem()




  def main(args: Array[String]) {

    // needed to run the route
    //implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future map/flatmap in the end
    implicit val executionContext = system.dispatcher

    val register = system.actorOf(Props[AsyncRegister],name = "syncRegister")
    register ! new ServiceRegisterMessage("ProductService",CommonConfigForAllService.CommonConstant.PRODUCT_SERVICE_IP,
       CommonConfigForAllService.CommonConstant.PRODUCT_SERVICE_PORT)

    val route : Route =
      (get & path("v1" / "product-service" / "_list" )) {
        println("Listing Service")
        onComplete(new AsyncListService().getAll()) {
          case Success(lst) => {
            complete(lst)
          }
          case Failure(_) => {
            complete(StatusCodes.InternalServerError)
          }
        }
       } ~
        (get & path("v1" / "product-service" / IntNumber)) { id =>
          onComplete(new AsyncGet().get(id)) {
            case Success(Some(s)) => {
               complete(s)
            }
            case Success(None) => {
               complete(StatusCodes.NotFound)
            }

            case Failure(_) => {
               complete(StatusCodes.InternalServerError)
            }
          }

        } ~
        (patch & path("v1" / "product-service" / IntNumber) & entity(as[ProductRepresentation])) { (id,store) =>
          //Not implemented yet
          onComplete(new AsyncPatch().patchRequest(id,store)) {
            case Success(Some(s)) => {
              complete(StatusCodes.OK)
            }
            case Success(None) => {
              complete(StatusCodes.NotFound)
            }
            case Failure(_) => {
              complete(StatusCodes.InternalServerError)
            }
          }

        } ~
        (delete & path("v1" / "product-service" / IntNumber)) { id =>
          onComplete(new AsyncDelete().delete(id)) {
            case Success(true) => {
              //println("Get")
              complete(StatusCodes.OK)
            }
            case Success(false) => {
              complete(StatusCodes.NotFound)
            }
            case Failure(_) => {
             complete(StatusCodes.InternalServerError)
            }
          }
        } ~
        (post & path("v1" / "product-service" / "create-id")) {

           onComplete(new AsyncCreateID().create()) {
             case Success(id) => {

               complete(id)
             }
             case Failure(id) => {
               complete(StatusCodes.InternalServerError)
             }
           }
        } ~
        (post & path("v1" / "product-service") & entity(as[ProductRepresentation])) {product => {
           onComplete(new AsyncCreateProduct().createProduct(product)) {
             case Success(representation) => {
                //println("test " + representation)
                //rep = representation
                complete(representation)

             }
             case Failure(_) => {
                complete(StatusCodes.InternalServerError)
             }
           }

        }}





    val bindingFuture = Http().bindAndHandle(route, "localhost", CommonConfigForAllService.CommonConstant.STORE_SERVICE_PORT)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ ⇒ system.terminate()) // and shutdown when done

  }
}
