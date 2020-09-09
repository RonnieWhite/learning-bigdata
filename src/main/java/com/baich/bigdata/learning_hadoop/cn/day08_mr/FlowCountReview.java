package com.baich.bigdata.learning_hadoop.cn.day08_mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReview {
    static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBeanReview> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\t");
            String phoneNR = fields[0];
            long dFlow = Long.parseLong(fields[1]);
            long uFlow = Long.parseLong(fields[2]);
            context.write(new Text(phoneNR), new FlowBeanReview(dFlow, uFlow));
        }
    }

    static class FlowCountReducer extends Reducer<Text, FlowBeanReview, Text, FlowBeanReview> {
        @Override
        protected void reduce(Text key, Iterable<FlowBeanReview> values, Context context) throws IOException, InterruptedException {
            long sumDFlow = 0;
            long sumUFlow = 0;
            for (FlowBeanReview value : values) {
                sumDFlow += value.getdFlow();
                sumUFlow += value.getuFlow();
            }
            FlowBeanReview resultBean = new FlowBeanReview(sumDFlow, sumUFlow);
            context.write(key, resultBean);
        }
    }


}
