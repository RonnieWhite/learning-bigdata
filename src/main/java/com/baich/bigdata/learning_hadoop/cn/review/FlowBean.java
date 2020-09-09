package com.baich.bigdata.learning_hadoop.cn.review;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements Writable {
    private long upFlow;
    private long dFlow;
    private long sumFlow;

    public FlowBean() {
    }

    public FlowBean(long upFlow, long dFlow) {
        this.upFlow = upFlow;
        this.dFlow = dFlow;
        this.sumFlow = upFlow + dFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public void setdFlow(long dFlow) {
        this.dFlow = dFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public long getdFlow() {
        return dFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(dFlow);
        out.writeLong(upFlow);
        out.writeLong(sumFlow);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        dFlow = in.readLong();
        upFlow = in.readLong();
        sumFlow = in.readLong();
    }

    @Override
    public String toString() {
        return dFlow + "\t" + upFlow + "\t" + sumFlow;
    }
}
