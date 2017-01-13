package StoreService.DataStore

import scala.collection.mutable
import StoreService.DomainModel
import scala.util.{Success, Try}
/**
 * Created by rasrivastava on 1/13/17.
 */
class DataAccessLayer {
  //throw exception
  def createStore(store : StoreService.DomainModel.Store) : Unit = {

    IdStore.map.put(store.id,store)


  }

  def getStore(storeId : Int) : Option[StoreService.DomainModel.Store] = {
    IdStore.map.get(storeId)
  }

  def listAllStores(max : Int) : List[StoreService.DomainModel.Store] = {
    val lstBuffer = new mutable.ListBuffer[StoreService.DomainModel.Store]
    IdStore.map.foreach(item  => {
      lstBuffer.+=(item._2)
    })



    lstBuffer.toList
  }

  def removeStore(storeId : Int) : Option[StoreService.DomainModel.Store] = {
    getStore(storeId) match {
      case Some(store) => {
        IdStore.map.remove(storeId)
        Some(store)
      }
      case None => None
    }
  }

  def updateStore(storeId : Int,updatedStore : StoreService.DomainModel.Store) : Option[StoreService.DomainModel.Store]= {
    getStore(storeId) match {
      case Some(_) => {
        IdStore.map.put(storeId,updatedStore)
        Some(updatedStore)
      }
      case None => None
    }
  }
}

object IdStore {
  val map = new mutable.HashMap[Int,StoreService.DomainModel.Store]()
}
