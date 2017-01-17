package ProductService.DomainModel

import ProductService.DataStore.DataAccessLayer

/**
 * Created by rasrivastava on 1/17/17.
 */
class ProductListService {
  def getAll() : List[Product] = {
    new DataAccessLayer().listAllProduct(100)
  }
}
