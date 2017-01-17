package ProductService.AsyncLayer

/**
 * Created by rasrivastava on 1/17/17.
 */

import ProductService.DomainModel.ProductRemoveService

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
class AsyncDelete {
  def delete(id : Int) : Future[Boolean] = Future {
    val retValue = new ProductRemoveService(id).remove()
    retValue match {
      case Some(oldValue) => true
      case None => false
    }
  }
}
