package learning_flink.course05_datastream

import java.{lang, util}

import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object DataStreamTransformationApp {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //    MapFilterFunc(env)
    //    unionFunc(env)
    splitSelectFunc(env)
    env.execute("DataStreamTransformationApp")
  }

  def MapFilterFunc(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[Long] = env.addSource(new CustomParallelSourceFunction).setParallelism(2)
    data.map(x => {
      println("received:" + x)
      x
    }).filter(_ % 2 == 0).print()
  }

  def unionFunc(env: StreamExecutionEnvironment): Unit = {
    val data1: DataStream[Long] = env.addSource(new CustomNonParallelSourceFunction)
    val data2: DataStream[Long] = env.addSource(new CustomNonParallelSourceFunction)
    data1.union(data2).print()
  }

  def splitSelectFunc(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[Long] = env.addSource(new CustomNonParallelSourceFunction)
    val splits: SplitStream[Long] = data.split(new OutputSelector[Long] {
      override def select(value: Long): lang.Iterable[String] = {
        val list = new util.ArrayList[String]()
        if (value % 2 == 0) {
          list.add("even")
        } else {
          list.add("odd")
        }
        list
      }
    })
//    splits.select("odd").print()
    splits.select("odd","even").print()
  }
}
