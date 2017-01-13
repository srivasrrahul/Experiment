package StoreService.WebInterface

import StoreService.APIModel.StoreRepresentation
import StoreService.DomainModel.StoreGetService
import StoreService.DomainModel.Store

/**
 * Created by rasrivastava on 1/13/17.
 */
class GetStoreDetails(val id : Int) {
  def getData() : Option[StoreRepresentation] = {
    val storeService = new StoreGetService(id).get()
    storeService match {
      case Some(x)  => Some(new StoreRepresentation(id,"","","",20,""))
      case None => None
    }


  }
}
