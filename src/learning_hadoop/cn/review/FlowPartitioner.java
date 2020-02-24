package learning_hadoop.cn.review;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

import java.util.HashMap;
import java.util.Map;

public class FlowPartitioner extends Partitioner<Text, FlowBean> {
    public static Map<String, Integer> provinceDict = new HashMap<>();

    static {
        provinceDict.put("136", 0);
        provinceDict.put("137", 1);
        provinceDict.put("138", 2);
        provinceDict.put("139", 3);
    }


    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        String prefix = text.toString().substring(0, 3);
        Integer provinceID = provinceDict.get(prefix);
        return provinceID == null ? 4 : provinceID;
    }
}
