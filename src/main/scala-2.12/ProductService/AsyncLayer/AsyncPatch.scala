package ProductService.AsyncLayer

/**
 * Created by rasrivastava on 1/17/17.
 */

import ProductService.APIModel.ProductRepresentation
import ProductService.DomainModel.ProductUpdateService
import ProductService.Utils.CommonUtils

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

class AsyncPatch {
  def patchRequest(id : Int,productRepr : ProductRepresentation) : Future[Option[ProductRepresentation]] = Future {
    new ProductUpdateService(id,CommonUtils.convertToProduct(productRepr)).update() match {
      case Some(oldProd) => {
        Some(CommonUtils.convertToProductRep(oldProd))
      }
      case None => {
        None
      }
    }
  }
}
