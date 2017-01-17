package ProductService.APIModel

/**
 * Created by rasrivastava on 1/17/17.
 */

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class ProductId(val id : Int) {

}

case class ProductRepresentation(val id : Int,
                   val name : String,
                   val description : String,
                   val slug : String,
                   val price : Int,
                   val image : String,
                   val storeId : Int,
                   val categories : List[String]) {}

case class ProductLst(val lst : List[ProductRepresentation]) {}

object ProductRepresentationJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport{
  implicit val productIdProtocol = jsonFormat1(ProductId)
  implicit val productProtocol = jsonFormat8(ProductRepresentation)
  implicit val productLstProtocol = jsonFormat1(ProductLst)
}
