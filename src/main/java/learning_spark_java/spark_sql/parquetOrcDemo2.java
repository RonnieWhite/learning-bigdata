package learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class parquetOrcDemo2 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("parquetOrcDemo2").setMaster("local");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        // 写入
        /**
         Dataset<Row> dataset = spark.read().format("json").load("E:/data/spark/person.json");
         dataset.write().parquet("E:/data/spark/abc.parquet");
         //        dataset.write().format("parquet").save("E:/data/spark/abc.parquet");
         */
        // 读取
        Dataset<Row> dataset = spark.read().format("parquet").load("E:/data/spark/person_name.parquet");
//        Dataset<Row> dataset = spark.read().parquet("E:/data/spark/person_name.parquet");
        spark.close();
    }
}
