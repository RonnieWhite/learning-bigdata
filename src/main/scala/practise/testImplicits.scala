package main.scala.practise

import java.io.File

import scala.io.Source

object testImplicits {

  class RichFile(val file: File) {
    def read() = {
      Source.fromFile(file.getName).mkString
    }
  }

  object RichFile {
    implicit def change(file: File) = new RichFile(file)
  }

  def main(args: Array[String]): Unit = {
    import RichFile.change
    new File("E:/").read()
  }


}
