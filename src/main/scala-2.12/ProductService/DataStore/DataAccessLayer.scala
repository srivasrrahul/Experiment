package ProductService.DataStore


import java.util.concurrent.locks.{ReentrantReadWriteLock, ReadWriteLock}

import scala.collection.mutable

/**
 * Created by rasrivastava on 1/17/17.
 */
class DataAccessLayer {
  def createProduct(product : ProductService.DomainModel.Product) : Unit = {
    IdStore.put(product.id,product)
  }

  def getProduct(productId : Int) : Option[ProductService.DomainModel.Product] = {
    IdStore.get(productId)
  }

  def listAllProduct(max : Int) : List[ProductService.DomainModel.Product] = {
//    val lstBuffer = new mutable.ListBuffer[ProductService.DomainModel.Product]
//    IdStore.map.foreach(item => {
//      lstBuffer.+=(item._2)
//    })
//
//    lstBuffer.toList
    IdStore.getAll()
  }

  def removeProduct(productId : Int) : Option[ProductService.DomainModel.Product] = {
    getProduct(productId) match {
      case Some(product) => {
        IdStore.remove(productId)
        Some(product)
      }
      case None => {
        None
      }
    }
  }

  def updateProduct(productId : Int,product : ProductService.DomainModel.Product) : Option[ProductService.DomainModel.Product] = {
    getProduct(productId) match {
      case Some(oldProduct) => {
        IdStore.put(productId,product)
        Some(oldProduct)
      }
      case None => None
    }
  }
}

object IdStore {
  def get(key : Int) : Option[ProductService.DomainModel.Product] = {
    lock.readLock().lock()
    try {
      map.get(key)
    }finally {
      lock.readLock().unlock()
    }
  }

  def getAll() : List[ProductService.DomainModel.Product] = {
    val lstBuffer = new mutable.ListBuffer[ProductService.DomainModel.Product]
    lock.readLock().lock()
    try {
      map.foreach(item => {
        lstBuffer.+=(item._2)
      })
    }finally {
      lock.readLock().unlock()
    }

    lstBuffer.toList
  }

  def put(key : Int,value : ProductService.DomainModel.Product) = {
    lock.writeLock().lock()
    try {
      map.put(key,value)
    } finally {
      lock.writeLock().unlock()
    }
  }

  def remove(key : Int) = {
    lock.writeLock().lock()
    try {
      map.remove(key)
    } finally {
      lock.writeLock().unlock()
    }
  }
  private val map = new mutable.HashMap[Int,ProductService.DomainModel.Product]
  private val lock: ReadWriteLock = new ReentrantReadWriteLock()
}

