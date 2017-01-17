package StoreService.DomainModel


import StoreService.DataStore.DataAccessLayer

/**
 * Created by Rahul on 1/13/17.
 */
class StoreRemoverService {
  def remove(store : Store) : Option[Store] = {
    val dataAccessLayer = new DataAccessLayer
    dataAccessLayer.removeStore(store.id)
  }

  def remove(storeId: Int)  : Option[Store] = {
    val dataAccessLayer = new DataAccessLayer
    dataAccessLayer.removeStore(storeId)
  }
}
