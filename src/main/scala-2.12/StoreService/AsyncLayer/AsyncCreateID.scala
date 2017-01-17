package StoreService.AsyncLayer

/**
 * Created by rasrivastava on 1/16/17.
 */

import StoreService.APIModel.StoreId
import StoreService.DomainModel.IdCreateService
import scala.concurrent.ExecutionContext.Implicits._

import scala.concurrent.Future
class AsyncCreateID {
  def createId() : Future[StoreId] = Future {
    val idService = new IdCreateService
    idService.create()
  }
}
