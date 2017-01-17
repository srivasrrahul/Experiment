package StoreService.AsyncLayer



/**
 * Created by rasrivastava on 1/16/17.
 */

import StoreService.DomainModel.StoreGetService
import StoreService.Utils.CommonUtils

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

import StoreService.APIModel.StoreRepresentation


class AsyncGet {
  def get(id : Int) : Future[Option[StoreRepresentation]] = Future {
    val store = new StoreGetService(id).get()
    store match {
      case Some(s) => {
        Some(CommonUtils.convertToRepr(s))
      }
      case None => {
        None
      }
    }
  }
}
