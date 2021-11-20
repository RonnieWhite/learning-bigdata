package course06_table_api_sql

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.Table
import org.apache.flink.api.scala._
import org.apache.flink.table.api.bridge.scala.BatchTableEnvironment
import org.apache.flink.types.Row


object TableSQLAPI {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val tableEnv: BatchTableEnvironment = BatchTableEnvironment.create(env)
    val csv: DataSet[person] = env.readCsvFile[person]("E:/data/flink/input/person.csv", ignoreFirstLine = true)
    val table: Table = tableEnv.fromDataSet(csv)
    tableEnv.registerTable("person", table)
    val result: Table = tableEnv.sqlQuery("SELECT avg(age) age FROM person")
    tableEnv.toDataSet[Row](result).print()
  }


  case class person(name: String, age: Int, job: String)

}
