package main.scala.learning_flink.course04_dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

object DataSetTransformationApp {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    //    mapFunc(env)
    filterFunc(env)
  }

  def mapFunc(env: ExecutionEnvironment): Unit = {
    val list: DataSet[Int] = env.fromCollection(List(1, 2, 3, 4, 5, 6))
    //    val list2: DataSet[Int] = list.map(x => x * 2)
    val list2: DataSet[Int] = list.map(_ * 2)
    list2.print()
  }

  def filterFunc(env: ExecutionEnvironment): Unit = {
    val list: DataSet[Int] = env.fromCollection(List(1, 2, 3, 4, 5, 6))
    val list2: DataSet[Int] = list.filter(_ > 5)
    list2.print()
  }

}
