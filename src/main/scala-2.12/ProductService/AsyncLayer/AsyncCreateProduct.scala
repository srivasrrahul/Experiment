package ProductService.AsyncLayer

import ProductService.APIModel.ProductRepresentation
import ProductService.DomainModel.ProductCreateService
import ProductService.Utils.CommonUtils
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

/**
 * Created by rasrivastava on 1/17/17.
 */
class AsyncCreateProduct {
  def createProduct(productRep : ProductRepresentation) : Future[ProductRepresentation] =  Future {
    val product = CommonUtils.convertToProduct(productRep)
    val createdProduct = new ProductCreateService(product).create()
    val createdProductRepr = CommonUtils.convertToProductRep(createdProduct)
    createdProductRepr
  }
}
