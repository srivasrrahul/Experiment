package LoadBalancer

import java.util.concurrent.locks.{ReadWriteLock, ReentrantReadWriteLock}

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
import scala.util.Random
import scala.util.control.Breaks._

/**
 * Created by rasrivastava on 1/17/17.
 */
class AsyncRegister {
  def register(registerRequest : RegisterEndpoint) : Future [Boolean] = Future {
    val serviceEndPoint = new ServiceEndpoint(registerRequest.name,registerRequest.ip,registerRequest.port)
    ServiceRegistry.put(serviceEndPoint.name,serviceEndPoint)
    true
  }


}

class AsyncGetService {
  def get(getServiceEndpoint: GetServiceEndpoint) : Future[String] = Future {
    val lstOpt : Option[List[ServiceEndpoint]] = ServiceRegistry.get(getServiceEndpoint.name)
    lstOpt match {
      case Some(lst : List[ServiceEndpoint]) => {
        //Generate a random between list and size
        val randomIndex = ServiceRegistry.random.nextInt(lst.size)
        var i = 0
        var retValue : ServiceEndpoint = null
        breakable {
          for (s <- lst) {
            if (i == randomIndex) {
              retValue = s
              break()
            }
          }

          i = i + 1
        }

        val finalStr = retValue.ip  + "," + retValue.port
        finalStr
      }
    }
  }

}

class ServiceEndpoint(val name : String,val ip : String,val port : Int) {

  override def toString = s"ServiceEndpoint($name, $ip, $port)"
}

object ServiceRegistry {
  val random = new Random(System.currentTimeMillis())
  def put(key : String,value : ServiceEndpoint) : Unit = {
    println(1)
    lock.writeLock().lock()
    try {

      val result : Option[List[ServiceEndpoint]]= map.get(key)
      println(result)
      result match {
        case Some(lst : List[ServiceEndpoint]) => {
          //println("Present")
          val result : List[ServiceEndpoint] = lst.filter(s => {
            s.ip == value.ip && s.port == value.port
          })



          if (result.size == 0) {
            val updatedLst = lst :+ value
          }



        }
        case None => {
          val lst = List[ServiceEndpoint](value)
          map.put(key,lst)

        }
      }
    }finally {
      lock.writeLock().unlock()
    }
  }
  def get(key : String) : Option[List[ServiceEndpoint]] = {
    lock.readLock().lock()
    try {
      map.get(key)
    } finally  {
      lock.readLock().unlock()
    }
  }
  private val lock: ReadWriteLock = new ReentrantReadWriteLock()
  private val map = new mutable.HashMap[String,List[ServiceEndpoint]]()
}
