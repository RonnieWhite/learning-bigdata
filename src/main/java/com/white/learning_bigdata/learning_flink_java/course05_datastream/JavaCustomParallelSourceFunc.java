package com.white.learning_bigdata.learning_flink_java.course05_datastream;

import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;

public class JavaCustomParallelSourceFunc implements ParallelSourceFunction<Long> {
    private long count = 1L;
    private boolean isRunning = true;

    @Override
    public void run(SourceContext<Long> ctx) throws Exception {
        while (isRunning && count < 10L) {
            ctx.collect(count);
            count += 1;
            Thread.sleep(1000);
        }

    }

    @Override
    public void cancel() {
        isRunning = false;

    }

}
