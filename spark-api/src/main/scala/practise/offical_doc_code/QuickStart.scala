package practise.offical_doc_code

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object QuickStart {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("quick_start").setMaster("local[1]")
    val sc = SparkContext.getOrCreate(conf)
    val textFile = sc.textFile("hdfs:///data/README.md")
    val lineWithSpark = textFile.filter(line => line.contains("Spark")).count()
    print(lineWithSpark)

  }

}
