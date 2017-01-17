package ProductService.DomainModel

/**
 * Created by rasrivastava on 1/17/17.
 */
class Product(val id: Int,val name : String,val desc : String) {


  //Skipping getters/setters
  var slug : Option[String] = None
  var price : Option[Int] = None
  var image : Option[String] = None
  var storeId : Option[Int] = None
  var categories : List[String] = List[String]()
}
