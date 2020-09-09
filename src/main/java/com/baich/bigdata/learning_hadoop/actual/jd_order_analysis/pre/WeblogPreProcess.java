package com.baich.bigdata.learning_hadoop.actual.jd_order_analysis.pre;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.baich.bigdata.learning_hadoop.actual.jd_order_analysis.mrbean.WebLogBean;
import com.baich.bigdata.learning_hadoop.actual.jd_order_analysis.mrbean.WebLogParser;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WeblogPreProcess {
    static class WeblogPreProcessMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        Set<String> pages = new HashSet<String>();
        Text k = new Text();
        NullWritable v = NullWritable.get();

        /**
         * 从外部加载网站url分类数据
         */
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            pages.add("/abount");
            pages.add("/black-ip-list/");
            pages.add("/cassandra-cluster/");
            pages.add("/finance-rhive-repurchase/");
            pages.add("/hadoop-family-roadmap/");
            pages.add("/hadoop-hive-intro/");
            pages.add("/hadoop-mahout-roadmap/");
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            WebLogBean webLogBean = WebLogParser.parser(line); // 解析资源
            // 过滤js/图片/css等静态资源
            WebLogParser.filtStaticResource(webLogBean, pages);
            k.set(webLogBean.toString());
            context.write(k, v);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(WeblogPreProcess.class);
        job.setMapperClass(WeblogPreProcessMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        FileInputFormat.setInputPaths(job, new Path("E:/data/webLog/input/access.log.fensi"));
//        FileOutputFormat.setOutputPath(job, new Path("E:/data/webLog/process_output/"));
        job.setNumReduceTasks(0);
        job.waitForCompletion(true);
    }
}
