package practise

object demo1 {
  def main(args: Array[String]): Unit = {
    //    println(sayHello("元芳"))
    //    val a: String = "Ronnie"
    //    print(s"你好，我的名字是$a")
    //    println(sayHi("小芳"))
    //    println(add(2, 3))
    //    println(testIf(7, 8))
    //    testFor()
    //    testTry()
    //    println(testMatch())
    //    testCallByValue(2 * 3, 4 * 5)
    //    testCallByName(2 * 3, 4 * 5)
    //    val x = Random.nextInt(10)
    // 匿名函数及调用
    //    val y = (x: Int) => x + 1
    //    println(y(7))
    //    println(y.apply(10))
    println(testCurried(2)(2))
  }

  def sayHello(name: String): String = {
    s"您好，我的名字是$name"
  }

  // scala足够聪明，可以判断返回值类型，所以返回值类型可不写
  def sayHi(name: String) = {
    s"您好，我的名字是$name"
  }

  // {}内就是代码块，如果只有一个表达式，{}可以省略
  def add(x: Int, y: Int) = x + y

  def testIf(x: Int, y: Int) = if (x >= y) x else y

  def testFor() = {
    val list: List[String] = List[String]("cici", "bobo", "eee")
    for (s <- list) {
      println(s)
    }
  }

  def testTry() = {
    try {
      Integer.parseInt("dog")
    } catch {
      case _ => 0
    } finally {
      println("Always be printed!")
    }
  }

  def testMatch() = {
    val code = 1
    code match {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "other"
    }
  }

  def testCallByValue(x: Int, y: Int) = {
    x * y
  }

  def testCallByName(x: => Int, y: => Int) = {
    println(x * y)
  }

  def testHighLevelFunc(f: (Int, Int) => Int) = {
    f(7, 8)
  }

  def testCurried(x: Int)(y: Int) = {
    x + y
  }
}
