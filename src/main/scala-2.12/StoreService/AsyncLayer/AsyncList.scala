package StoreService.AsyncLayer

import StoreService.APIModel.StoreRepresentation
import StoreService.DomainModel.StoreListService
import StoreService.Utils.CommonUtils

import scala.collection.mutable.ListBuffer

/**
 * Created by rasrivastava on 1/16/17.
 */
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

class AsyncList {
  def get() : Future[List[StoreRepresentation]]  = Future {

    val lst = new StoreListService().getAll()
    val retValue = new ListBuffer[StoreRepresentation]
    lst.foreach(x => retValue.+=(CommonUtils.convertToRepr(x)))

    retValue.toList
  }
}
