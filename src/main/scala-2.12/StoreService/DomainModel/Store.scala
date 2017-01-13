package StoreService.DomainModel

/**
 * Created by Rahul on 1/13/17.
 */
//Mandatory is Int
class Store(val id : Int,val name : String,val description : String) {
  def addSlug(slugProvided : String) : Store = {
    slug = Some(slugProvided)
    this
  }

  def addDefaultCashback(cashBackProvided : Long) : Store = {
    cashBack = Some(cashBackProvided)
    this
  }

  def addImage(imageUrlProvided : String) : Store = {
    image = Some(imageUrlProvided)
    this
  }

  var slug  : Option[String] = None
  var cashBack : Option[Long] = None
  var image : Option[String] = None
}
