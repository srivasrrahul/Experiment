package StoreService.DomainModel

import StoreService.DataStore.DataAccessLayer

/**
 * Created by rasrivastava on 1/13/17.
 */
class StoreListService {
  //In ideal world we'll return a max number and have a page link associated
  //In V0 max doesn't have an effect
  def getAll() : List[Store] = {
    val dataAccessLayer = new DataAccessLayer
    dataAccessLayer.listAllStores(100)
  }
}
