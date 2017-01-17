package LoadBalancer

/**
 * Created by rasrivastava on 1/17/17.
 */
import scala.util.{Success, Failure}

//import akka.actor.Status.Success
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
import spray.json.DefaultJsonProtocol





import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

//import scala.util.Success




object WebServer {





  import LoadBalancer.ProductRepresentationJsonProtocol._

  implicit val executionContext :ExecutionContext = ExecutionContext.global
//
//
//
//
  def main(args: Array[String]) {

    // needed to run the route
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future map/flatmap in the end
    implicit val executionContext = system.dispatcher

    //ONLY STUB CODE for testing routing.
    val route : Route =
      (post & path("v1" / "load-balancer" / "entry") & entity(as[RegisterEndpoint])) {registerMsg => {
         onComplete(new AsyncRegister().register(registerMsg)) {
           case Success(_) => {
             complete(StatusCodes.OK)
           }
           case Failure(_) => {
             complete(StatusCodes.InternalServerError)
           }
         }
       }} ~
        (get & path("v1" / "load-balancer" / "entry") & entity(as[GetServiceEndpoint])) { name =>
          onComplete(new AsyncGetService().get(name)) {
           case Success(x) => {
             complete(201 -> x)
           }
           case Failure(_) => {
             complete(StatusCodes.NotFound)
           }
         }

        }




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

    val bindingFuture = Http().bindAndHandle(route, "localhost", CommonConfigForAllService.CommonConstant.STORE_SERVICE_PORT)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ ⇒ system.terminate()) // and shutdown when done

  }
}

