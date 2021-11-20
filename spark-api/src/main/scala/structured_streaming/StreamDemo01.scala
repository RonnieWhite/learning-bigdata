package structured_streaming

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Author: Chenghui Bai
  * Date: 2021/4/13 9:51
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.learning_spark.structured_streaming
  * ClassName: StreamDemo01
  * Version:
  * Description:
  */
object StreamDemo01 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("spark://vm01:7077").setAppName("StreamDemo01")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val lines: DataFrame = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()
    import spark.implicits._
    val words = lines.as[String].flatMap(_.split(" "))
    //    words.show()
    val wordCounts = words.groupBy("value").count()
    val query = wordCounts.writeStream
      .outputMode("complete")
      // can be "orc", "json", "csv", "kafka","console", "memory" etc.
      .format("console")
      .start()
    query.awaitTermination()
  }
}
