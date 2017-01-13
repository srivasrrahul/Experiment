package StoreService.DomainModel

import StoreService.APIModel.StoreId
import StoreService.DataStore.IdService

/**
 * Created by Rahul on 1/13/17.
 */
class IdCreateService {
  def create() : StoreId = {
    new StoreId(IdService.getNewId())
  }
}
