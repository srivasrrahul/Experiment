package StoreService.Utils

import StoreService.APIModel.StoreRepresentation
import StoreService.DomainModel.Store

/**
 * Created by Rahul on 1/16/17.
 */
object CommonUtils {
  val host = "http://localhost:8080/"
  val versionInuse = "v1/"
  val uriBase = host + versionInuse + "store-service/"
  def convertToRepr(store: Store) : StoreRepresentation = {
    var slug = ""
    store.slug match {
      case Some(x) => slug = x
    }

    var cashBack : Long = 0
    store.cashBack match {
      case Some(x) => cashBack = x
    }

    var image : String = ""
    store.image match {
      case Some(x) => image = x
    }
    new StoreRepresentation(store.id,store.name,store.description,slug,cashBack,image)
  }

  def convertToStore(store: StoreRepresentation) : Store = {

    val newStore = new Store(store.id,store.name,store.description)
    newStore.slug = Some(store.slug)
    newStore.cashBack = Some(store.cashback)
    newStore.image = Some(store.image)
    newStore
  }
}
