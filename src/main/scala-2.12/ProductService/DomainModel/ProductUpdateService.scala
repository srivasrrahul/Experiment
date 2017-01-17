package ProductService.DomainModel

import ProductService.DataStore.DataAccessLayer

/**
 * Created by rasrivastava on 1/17/17.
 */
class ProductUpdateService(val id : Int,val updatedProduct : Product) {
  def update() : Option[Product] = {
    new DataAccessLayer().updateProduct(id,updatedProduct)
  }
}
