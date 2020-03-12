package main.scala.learning_spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object SQLDemo {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("testSparkSession").setMaster("local")
    val session: SparkSession = SparkSession.builder().appName("test").config(conf).getOrCreate()
    import session.implicits._
    //    val df: DataFrame = session.read.csv("E:/data/spark/person.csv")
    //    session.read.schema("a INT, b STRING, c DOUBLE").csv("test.csv")
    val df: DataFrame = session.read.format("csv").option("header", "true").load("E:/data/spark/person.csv")

    //    df.show()
    //    df.createTempView("person")
    //    df.createGlobalTempView("person")
    //    session.sql("SELECT age,avg(salary) AS salary FROM person GROUP BY age").show()
    //    session.close()
    //    session.newSession().sql("SELECT age,avg(salary) AS salary FROM person GROUP BY age").show()
    Seq(person(10, "Jojo", 32)).toDS()
    session.close()

  }

  case class person(id: Int, name: String, salary: Float)

}
