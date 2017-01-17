package ProductService.DataStore

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by rasrivastava on 1/17/17.
 */
object IdProductService {
  val id = new AtomicInteger(0)
  def getNewId() : Int = {
    id.incrementAndGet()
  }
}
