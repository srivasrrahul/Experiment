package ProductService.DomainModel

import ProductService.DataStore.DataAccessLayer

/**
 * Created by rasrivastava on 1/17/17.
 */
class ProductRemoveService(val id : Int) {
  def remove() : Option[Product] = {
    new DataAccessLayer().removeProduct(id)
  }
}
