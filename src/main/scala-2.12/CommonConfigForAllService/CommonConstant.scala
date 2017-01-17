package CommonConfigForAllService

/**
 * Created by rasrivastava on 1/17/17.
 */
//This file is compiled across multiple services. So keep only config here
object CommonConstant {
  val STORE_SERVICE_IP = "localhost"
  val STORE_SERVICE_PORT = 8080
  val PRODUCT_SERVICE_IP = "localhost"
  val PRODUCT_SERVICE_PORT = 9080
  val LB_SERVICE_HOST = "127.0.0.1"
  val LB_SERVICE_PORT = 10080
}
