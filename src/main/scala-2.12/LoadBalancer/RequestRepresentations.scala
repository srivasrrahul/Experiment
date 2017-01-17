package LoadBalancer


import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
 * Created by rasrivastava on 1/17/17.
 */
case class RegisterEndpoint(val name : String,val ip : String,val port : Int) {

}

case class UnRegisterEndpoint(val name : String,val ip : String,val port : Int) {

}

case class GetServiceEndpoint(val name : String) {

}

object ProductRepresentationJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport{
  implicit val registerProtocol = jsonFormat3(RegisterEndpoint)
  implicit val unregisterProtocol = jsonFormat3(UnRegisterEndpoint)
  implicit val getServiceProtocol = jsonFormat1(GetServiceEndpoint)
}
