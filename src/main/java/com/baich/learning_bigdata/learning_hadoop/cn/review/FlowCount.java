package com.baich.learning_bigdata.learning_hadoop.cn.review;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCount {
    static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\t");
            String phoneNB = fields[0];
            long dFlow = Long.parseLong(fields[1]);
            long upFlow = Long.parseLong(fields[2]);
            long sumFlow = dFlow + upFlow;
            FlowBean bean = new FlowBean(upFlow, dFlow);
            context.write(new Text(phoneNB), bean);
        }
    }

    static class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            long sum_dFlow = 0;
            long sum_upFlow = 0;
            for (FlowBean bean : values) {
                sum_dFlow += bean.getdFlow();
                sum_upFlow += bean.getUpFlow();
            }
            FlowBean res_bean = new FlowBean(sum_upFlow, sum_dFlow);
            context.write(key, res_bean);
        }
    }

    public void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance();
        System.setProperty("HADOOP_USER_NAME", "sparkuser");
        job.setJarByClass(FlowCount.class);
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean res = job.waitForCompletion(true);
        System.out.println(res ? 0 : 1);


    }
}
