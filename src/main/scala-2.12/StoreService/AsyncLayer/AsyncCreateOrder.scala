package StoreService.AsyncLayer

import StoreService.APIModel.StoreRepresentation
import StoreService.Utils.CommonUtils

import scala.concurrent.Future
import StoreService.DomainModel.{StoreCreateService, Store}
import scala.concurrent.ExecutionContext.Implicits._

/**
 * Created by rasrivastava on 1/16/17.
 */
class AsyncCreateOrder {
  def createStore(storeRep : StoreRepresentation): Future[StoreRepresentation] = Future {
    val store = CommonUtils.convertToStore(storeRep)
    val createdStore = new StoreCreateService(store).create()
    val representation = CommonUtils.convertToRepr(createdStore)
    representation
  }
}
