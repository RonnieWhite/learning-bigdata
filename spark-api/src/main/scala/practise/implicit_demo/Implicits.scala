package practise.implicit_demo

/**
  * Author: Chenghui Bai
  * Date: 2021/1/4 15:31
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.practise.implicit_demo
  * ClassName: Implicits
  * Version:
  * Description:
  */
object Implicits {
  implicit def intToString(msg: Int): String = {
    msg.toString
  }

}
