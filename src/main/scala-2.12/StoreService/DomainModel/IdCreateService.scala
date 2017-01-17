package StoreService.DomainModel

import StoreService.APIModel.StoreId
import StoreService.DataStore.IdService
import scala.concurrent.ExecutionContext.Implicits._

/**
 * Created by Rahul on 1/13/17.
 */
class IdCreateService {
  def create() : StoreId = {
    new StoreId(IdService.getNewId())
  }
}
