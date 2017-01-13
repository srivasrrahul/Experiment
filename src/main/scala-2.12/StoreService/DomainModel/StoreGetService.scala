package StoreService.DomainModel

import StoreService.DataStore.DataAccessLayer

/**
 * Created by Rahul on 1/13/17.
 */
class StoreGetService(val id : Int) {
  def get(): Option[Store] = {
    val daoLayer = new DataAccessLayer
    daoLayer.getStore(id)
  }
}
