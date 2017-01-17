package ProductService.AsyncLayer

import ProductService.APIModel.ProductId
import ProductService.DomainModel.IdCreateService
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

/**
 * Created by rasrivastava on 1/17/17.
 */
class AsyncCreateID {
  def create() : Future[ProductId] = Future {
    new IdCreateService().create()
  }
}
