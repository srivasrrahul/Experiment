package StoreService.DataStore

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by rasrivastava on 1/13/17.
 */
object IdService {
  val id = new AtomicInteger(0)
  def getNewId() : Int = {
    id.incrementAndGet()
  }
}
