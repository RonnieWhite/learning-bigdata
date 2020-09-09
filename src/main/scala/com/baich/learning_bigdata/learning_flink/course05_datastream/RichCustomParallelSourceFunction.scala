package com.baich.learning_bigdata.learning_flink.course05_datastream

import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}

class RichCustomParallelSourceFunction extends RichParallelSourceFunction[Long] {
  var counter = 1L
  var isRunning = true

  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    while (isRunning && counter < 10) {
      ctx.collect(counter)
      counter += 1
      Thread.sleep(1000)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
  }
}
