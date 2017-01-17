package ProductService.AsyncLayer

/**
 * Created by Rahul on 1/17/17.
 */

import ProductService.APIModel.ProductRepresentation
import ProductService.DomainModel.ProductListService
import ProductService.Utils.CommonUtils

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

class AsyncListService {
  def getAll() : Future[List[ProductRepresentation]] = Future {
    val lstBuffer = new ListBuffer[ProductRepresentation]
    new ProductListService().getAll().foreach(product => {
      lstBuffer.+=(CommonUtils.convertToProductRep(product))
    })

    lstBuffer.toList
  }
}
