package main.java.learning_spark.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class parquetOrcDemo1 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("parquetOrcDemo1").setMaster("local");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        /**
         Dataset<Row> dataset = spark.read().json("E:/data/spark/person.json");
         // 存储为parquet文件
         dataset.select("name").write().save("E:/data/spark/tmp.parquet");
         // 指定存储文件的格式
         Dataset<Row> dataset = spark.read().json("E:/data/spark/person.json");
         dataset.write().format("parquet").save("E:/data/spark/tmp.parquet");
         */
        // format csv文件
        /**
         Dataset<Row> dataset = spark.read().format("csv")
         .option("sep", ",")
         .option("header", "true")
         .load("E:/data/spark/person.csv");
         dataset.show();
         */
        // orc文件
        /**
         Dataset<Row> dataset = spark.read().format("csv")
         .option("sep", ",")
         .option("header", "true")
         .load("E:/data/spark/person.csv");
         dataset.write().format("orc")
         //                .option("orc.bloom.filter.columns","favorite_color") // 过滤列
         .option("orc.dictionary.key.threshold", "1.0")
         .save("E:/data/spark/tmp.orc");
         */
        // 读取parquet文件
        /**
         Dataset<Row> dataset = spark.read().load("E:/data/spark/person_name.parquet");
         dataset.show();
         */
        // 读取orc文件
        /**
         Dataset<Row> dataset = spark.read().format("orc").load("E:/data/spark/person.orc");
         dataset.show();
         */

//        Dataset<Row> dataset = spark.sql("SELECT * FROM parquet.`E:/data/spark/person_name.parquet`");
        Dataset<Row> dataset = spark.sql("SELECT * FROM orc.`E:/data/spark/person.orc`");
        dataset.show();

        spark.close();
    }
}
