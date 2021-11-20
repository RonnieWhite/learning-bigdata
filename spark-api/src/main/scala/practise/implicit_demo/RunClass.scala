package practise.implicit_demo

/**
  * Author: Chenghui Bai
  * Date: 2021/1/4 15:29
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.practise.implicit_demo
  * ClassName: RunClass
  * Version:
  * Description:
  */
object RunClass {
  def main(args: Array[String]): Unit = {

    foo(100)
  }

  def foo(msg: Int) = {
    import Implicits._
    print(msg)
  }

}
