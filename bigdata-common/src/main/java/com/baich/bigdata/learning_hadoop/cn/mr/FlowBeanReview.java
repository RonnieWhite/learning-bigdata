package com.baich.bigdata.learning_hadoop.cn.mr;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBeanReview implements Writable {

    private long dFlow;
    private long uFlow;
    private long sumFlow;

    public FlowBeanReview() {
    }

    public FlowBeanReview(long dFlow, long uFlow) {
        this.dFlow = dFlow;
        this.uFlow = uFlow;
        this.sumFlow = dFlow + uFlow;
    }

    public void setdFlow(long dFlow) {
        this.dFlow = dFlow;
    }

    public void setuFlow(long uFlow) {
        this.uFlow = uFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public long getdFlow() {
        return dFlow;
    }

    public long getuFlow() {
        return uFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(dFlow);
        out.writeLong(uFlow);
        out.writeLong(sumFlow);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        dFlow = in.readLong();
        uFlow = in.readLong();
        sumFlow = in.readLong();
    }

    @Override
    public String toString() {
        return uFlow + "\t" + dFlow + "\t" + sumFlow;
    }
}
