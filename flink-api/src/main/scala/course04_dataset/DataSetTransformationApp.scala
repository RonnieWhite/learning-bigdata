package course04_dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

import scala.collection.mutable.ListBuffer

object DataSetTransformationApp {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    //    mapFunc(env)
    //    filterFunc(env)
    //    mapPartitionFunc(env)
    //    flatMapFunc(env)
//    joinFunc(env)
    crossFunc(env)
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

  def mapPartitionFunc(env: ExecutionEnvironment): Unit = {
    val list: DataSet[Int] = env.fromCollection(List(1, 2, 3, 4, 5, 6))
    val list2: DataSet[Int] = list.mapPartition(x => x map (_ * 2))
    list2.first(2).print()
  }

  def flatMapFunc(env: ExecutionEnvironment): Unit = {
    val buffer: ListBuffer[String] = ListBuffer[String]()
    buffer.append("andy,judy")
    buffer.append("julia,core")
    buffer.append("cc,jj,cc,judy")
    val data: DataSet[String] = env.fromCollection(buffer)
    //    val data2: DataSet[String] = data.flatMap(x => x.split(","))
    val data2: DataSet[String] = data.flatMap(_.split(","))
    //    data.print()
    //    data2.print()
    val data3: DataSet[String] = data2.distinct()
    data3.print()
  }

  def joinFunc(env: ExecutionEnvironment): Unit = {
    val info1: ListBuffer[(Int, String)] = ListBuffer[(Int, String)]()
    val info2: ListBuffer[(Int, String)] = ListBuffer[(Int, String)]()
    info1.append((1, "andy"))
    info1.append((2, "bob"))
    info1.append((3, "cora"))
    info1.append((4, "davis"))

    info2.append((1, "andrew"))
    info2.append((2, "bryant"))
    val data1: DataSet[(Int, String)] = env.fromCollection(info1)
    val data2: DataSet[(Int, String)] = env.fromCollection(info2)
    //    val data3: DataSet[(Int, String, String)] = data1.join(data2)
    //      .where(0)
    //      .equalTo(0)
    //      .apply((first, second) => (first._1, first._2, second._2))
    //    data3.print()
    //    val data3: DataSet[(Int, String, String)] = data1.leftOuterJoin(data2)
    //      .where(0)
    //      .equalTo(0)
    //      .apply((first, second) => {
    //        if (second == null) {
    //          (first._1, first._2, "-")
    //        } else {
    //          (first._1, first._2, second._2)
    //        }
    //      })
    //    data3.print()
    val data3: DataSet[(Int, String, String)] = data1.fullOuterJoin(data2)
      .where(0)
      .equalTo(0)
      .apply((first, second) => {
        if (first == null) {
          (first._1, first._2, "-")
        } else if (second == null) {
          (first._1, first._2, "-")
        } else {
          (first._1, first._2, second._2)
        }
      })
    data3.print()
  }


  def crossFunc(env: ExecutionEnvironment): Unit = {
    val info1 = List("曼城", "曼联")
    val info2 = List(1, 2, 3)
    val data1: DataSet[String] = env.fromCollection(info1)
    val data2: DataSet[Int] = env.fromCollection(info2)
    val data3: CrossDataSet[String, Int] = data1.cross(data2)
    data3.print()
  }
}
