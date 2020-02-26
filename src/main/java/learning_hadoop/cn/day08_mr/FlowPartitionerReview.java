package main.java.learning_hadoop.cn.day08_mr;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

import java.util.HashMap;

public class FlowPartitionerReview extends Partitioner<Text, FlowBeanReview> {

    public static HashMap<String, Integer> provinceDict = new HashMap<String, Integer>();

    static {
        provinceDict.put("136", 0);
        provinceDict.put("138", 1);
        provinceDict.put("139", 2);
        provinceDict.put("177", 3);
        provinceDict.put("155", 4);
    }

    @Override
    public int getPartition(Text text, FlowBeanReview flowBeanReview, int numPartitions) {
        String prefix = text.toString().substring(0, 3);
        Integer provinceID = provinceDict.get(prefix);
        return provinceID == null ? 4 : provinceID;
    }
}
