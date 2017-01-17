package ProductService.DomainModel

import ProductService.DataStore.DataAccessLayer

/**
 * Created by rasrivastava on 1/17/17.
 */
class ProductCreateService(val product : Product) {
  def create() : Product = {
    val dataAccessLayer = new DataAccessLayer
    dataAccessLayer.createProduct(product)
    product
  }

}
