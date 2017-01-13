package StoreService.DomainModel

import StoreService.DataStore.DataAccessLayer

/**
 * Created by Rahul on 1/13/17.
 */
class StoreCreateService(val store: Store) {
  //In real world there will be errors so type will change
  def create() : Store = {
    val dataAccessLayer = new DataAccessLayer
    dataAccessLayer.createStore(store)
    store

  }
}
