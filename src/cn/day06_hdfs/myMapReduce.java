package cn.day06_hdfs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class myMapReduce {

    public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        // map 方法的生命周期：框架每传一行数据就被调用一次
        // key 这一行的起始点在文件中的偏移量
        // value 这一行的内容
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException{

        }
    }
}
