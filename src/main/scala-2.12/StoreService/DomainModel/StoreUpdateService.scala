package StoreService.DomainModel

import StoreService.DataStore.DataAccessLayer

/**
 * Created by rasrivastava on 1/13/17.
 */
class StoreUpdateService {
  def update(store : Store) : Option[Store] = {
    val dataAccessLayer = new DataAccessLayer
    dataAccessLayer.updateStore(store.id,store) match {
      case Some(x) => Some(x)
      case None => None
    }
  }
}
