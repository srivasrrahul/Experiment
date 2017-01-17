package ProductService.DomainModel

import ProductService.DataStore.DataAccessLayer

/**
 * Created by rasrivastava on 1/17/17.
 */
class ProductGetService(val id : Int) {
  def get(): Option[Product] = {
    new DataAccessLayer().getProduct(id)
  }

}
