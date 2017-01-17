package ProductService.AsyncLayer

/**
 * Created by rasrivastava on 1/17/17.
 */

import ProductService.APIModel.ProductRepresentation
import ProductService.DomainModel.ProductGetService
import ProductService.Utils.CommonUtils

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
class AsyncGet {
  def get(id : Int) : Future[Option[ProductRepresentation]] = Future {
    val productOption = new ProductGetService(id).get()
    productOption match {
      case Some(product) => {
        Some(CommonUtils.convertToProductRep(product))
      }
      case None => {
        None
      }
    }
  }
}
