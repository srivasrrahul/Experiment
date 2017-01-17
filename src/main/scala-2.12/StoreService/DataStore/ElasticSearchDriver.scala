package StoreService.DataStore

//Plan to complete it but skipping due to lack of time

//import scala.concurrent.{Future, Await, ExecutionContext}
//import StoreService.APIModel.ServiceJsonProtoocol._
//import akka.actor._
//import akka.http.javadsl.model.headers.RawHeader
//import akka.http.scaladsl.Http
//import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
//import akka.http.scaladsl.model._
//import akka.http.scaladsl.unmarshalling.Unmarshal
//import akka.protobuf.ByteString
//import akka.stream.ActorMaterializer
//import akka.stream.ActorMaterializerSettings
//
//import scala.concurrent.duration.Duration
//import akka.util.Timeout
//
////import akka.typed.ActorRef
//import spray.json.DefaultJsonProtocol
//import akka.http.scaladsl._
//import scala.concurrent.duration._
//
//
//import scala.Array
//
//import spray.json.DefaultJsonProtocol
//import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
//
//import scala.util.Success
//import scala.util.Try
//
//case class Version(val _index : String,val _type : String,val _id : String,val _version : Int) {}
//
//trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{
//  implicit val customerProtocol = jsonFormat4(Version)
//}
//object DBConstant {
//  val dbHostPost = "localHost:9200"
//  //Any unique URI, we'll use its version
//  val uniqueIdURI = "http://" + dbHostPost + "/" + "id-creator-unique/unique-id/1001"
//}
//case object GetUniqueId
//case object GetUniqueIdSuccess
//case object GetUniqueIdFailure
//
//class ElasticSearchUniqueIdDriver extends Actor with JsonSupport {
//
//  import akka.pattern.pipe
//  import context.dispatcher
//
//  final implicit val materializer: ActorMaterializer = ActorMaterializer()
//  implicit val executionContext :ExecutionContext = ExecutionContext.global
//
//  val http = Http(context.system)
//
////  override def preStart() = {
////
////
////  }
//
//
//
//  def receive = {
//    case HttpResponse(StatusCodes.OK | StatusCodes.Created, headers, entity, _) =>
//      //log.info("Got response, body: " + entity.dataBytes.runFold(ByteString(""))(_ ++ _))
//      println("Created or Fetched" + entity)
//      val um = Unmarshal(entity).to[Version]
//      val temp : Option[Try[Version]] = um.value
//      temp match {
//        case Some(Success(x)) => {
//          originalSender.get ! ((GetUniqueIdSuccess,x._version))
//        }
//      }
//
//
//
//
//
//    case HttpResponse(code, _, _, _) =>
//      println("Request failed, response code: " + code)
//      originalSender.get ! (GetUniqueIdFailure)
//
//    case GetUniqueId => {
//
//      originalSender = Some(sender())
//      var httpRequest : HttpRequest = HttpRequest(HttpMethods.POST,
//                                                   uri = DBConstant.uniqueIdURI)
//
//
//      httpRequest = httpRequest.withEntity("{\"dummy\": 1}")
//      //println(httpRequest.toString)
//      http.singleRequest(httpRequest).pipeTo(self)
//
//    }
//    context.stop(self)
//  }
//
//  var originalSender : Option[ActorRef] = None
//
//}
//
//object Main extends App {
//  val system = ActorSystem("TestSystem")
//  val uniqueIdActor = system.actorOf(Props[ElasticSearchUniqueIdDriver],name = "testActor")
//
//  implicit val timeout = Timeout(5 seconds)
//  //val future : scala.concurrent.Future[Int] = uniqueIdActor ! (GetUniqueId)
//
//  //Await.result(future,timeout.duration).asInstanceOf[Int]
//
//
//  system.shutdown()
//
//}
//
