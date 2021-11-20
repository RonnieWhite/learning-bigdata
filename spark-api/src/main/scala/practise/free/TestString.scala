package practise.free

object TestString {
  def main(args: Array[String]): Unit = {
    val str = "hello"
    //    charArrPrint(str)
    //    foreachPrint(str)
    //    forPrint(str)
    //    filterPrint(str)
    ifStringPrint()

  }

  // java方法
  def charArrPrint(str: String): Unit = {
    println("====java====")
    val array = str.toCharArray
    for (s <- array) {
      println(s)
    }
  }

  // foreach
  def foreachPrint(str: String): Unit = {
    println("====foreach====")
    str.foreach(println)
  }

  // for
  def forPrint(str: String): Unit = {
    println("====for循环====")
    for (s <- str) {
      println(s)
    }
  }

  // filter
  def filterPrint(str: String): Unit = {
    println("====过滤====")
    val filterStr = str.filter(_ != 'l')
    println(str)
    println(filterStr)
  }

  def ifStringPrint(): Unit = {
    val a = 7
    val b = 6
    val absValue = if (a > b) true else false
    println(absValue)
  }


}
