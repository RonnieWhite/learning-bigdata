package main.java.learning_hadoop.cn.review;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

import java.util.HashMap;
import java.util.Map;

public class MapSideJoinReview {
    public static class MapSideJoinMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        Map<String, String> pdInfoMap = new HashMap<>();
        Text k = new Text();

    }
}
