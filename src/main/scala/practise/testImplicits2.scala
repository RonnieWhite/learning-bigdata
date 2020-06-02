package practise

import java.util.Date

object testImplicits2 {

  class myDate(val date: Date) {
    def printDate(): Unit = {
      println(date.getYear)
    }
  }

  object myImp {
    implicit def toMyDate(date: Date) = new myDate(date: Date)
  }

  def main(args: Array[String]): Unit = {
    import myImp._
    new Date().printDate()
  }
}
