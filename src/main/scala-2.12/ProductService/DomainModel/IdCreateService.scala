package ProductService.DomainModel

import ProductService.APIModel.ProductId
import ProductService.DataStore.IdProductService

/**
 * Created by rasrivastava on 1/17/17.
 */
class IdCreateService {
  def create() : ProductId = {
    new ProductId(IdProductService.getNewId())
  }
}
