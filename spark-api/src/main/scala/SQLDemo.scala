import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * Created By IDEA.
  * Author : BaiCH
  * Date : Created in 2021-11-20
  * Time : 11:41
  * Description:
  * Modified By:
  * version : v1.0
  */
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
    val persons: Seq[person] = Seq(person(10, "Jojo", 32))
    val value: Dataset[person] = persons.toDS()
    value.show()
    session.close()

  }

  case class person(id: Int, name: String, salary: Float)

}
