package practise

import org.apache.spark.sql.SparkSession

object demo2 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("1").master("local[2]").getOrCreate()
    import spark.implicits._
    val df = spark.read.json("1.json")
    df.show()
  }

}
