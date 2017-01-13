package StoreService.APIModel

/**
 * Created by rasrivastava on 1/13/17.
 */


import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class StoreRepresentation (val id : Int,val name : String,val description : String,val slug : String,val cashback : Long,val image : String ) {

}

case class StoreId(val id : Int) {

}

case class StoreLst(val lst : StoreRepresentation) {

}



object ServiceJsonProtoocol extends DefaultJsonProtocol with SprayJsonSupport{
  implicit val customerProtocol = jsonFormat6(StoreRepresentation)
  implicit val storeProtocol = jsonFormat1(StoreId)
  implicit val storeListProtocol = jsonFormat1(StoreLst)
}


