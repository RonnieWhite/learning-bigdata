package main.learning_hadoop.cn.day08_mr.wcdemo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * KEYIN, VALUEIN 对应mapper输出的KEYOUT，VALUEOUT类型对应
 * KEYOUT, VALUEOUT是自定义reduce逻辑处理结果的输出数据类型
 * KEYOUT是单词
 * VALUEOUT是总次数
 *
 * @author white
 */
public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable value : values) {
            count += value.get();
        }
        context.write(key, new IntWritable(count));
    }

}
