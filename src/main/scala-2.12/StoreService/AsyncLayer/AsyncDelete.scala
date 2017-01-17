package StoreService.AsyncLayer

/**
 * Created by rasrivastava on 1/16/17.
 */

import StoreService.APIModel.{StoreId, StoreRepresentation}
import StoreService.DomainModel.StoreRemoverService
import StoreService.Utils.CommonUtils

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
class AsyncDelete {
  def delete(id: Int) : Future[Boolean] = Future {
    //val store = CommonUtils.convertToStore(storeRepresentation)
    val removerServiceResponse = new StoreRemoverService().remove(id)
    removerServiceResponse match {
      case Some(_) => true
      case None => false
    }
  }
}
