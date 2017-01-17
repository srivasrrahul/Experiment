package ProductService.Utils

import ProductService.APIModel.ProductRepresentation

/**
 * Created by rasrivastava on 1/17/17.
 */
object CommonUtils {

  def convertToProductRep(product : ProductService.DomainModel.Product) : ProductRepresentation = {
    var slug = ""
    product.slug match {
      case Some(s) => {
        slug = s
      }
      case None => {

      }
    }

    var price = -1
    product.price match {
      case Some(p) => {
        price = p
      }
      case None => {

      }
    }

    var image = ""
    product.image match {
      case Some(img) => {
        image = img
      }
      case None => {

      }
    }

    var id = -1
    product.storeId match {
      case Some(localId) => {
        id = localId
      }
      case None => {

      }
    }

//    var categories= List[String]()
//    product.categories match {
//      case Some(lst) => {
//        categories = lst
//      }
//      case None => {
//
//      }
//    }

    new ProductRepresentation(product.id,product.name,product.desc,slug,price,image,id,product.categories)
  }

  def convertToProduct(productRepresentation: ProductRepresentation) : ProductService.DomainModel.Product = {
    val product = new ProductService.DomainModel.Product(productRepresentation.id,productRepresentation.name,productRepresentation.description)
    product.slug = Some(productRepresentation.slug)
    product.price = Some(productRepresentation.price)
    product.image = Some(productRepresentation.image)
    product.storeId = Some(productRepresentation.id)
    product.categories = productRepresentation.categories
    product
  }

}
