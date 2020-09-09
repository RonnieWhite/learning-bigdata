package com.baich.learning_bigdata.learning_hadoop.cn.day09_actual;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 订单表和商品表合在一起
 */
public class RJoin {
    static class RJoinMapper extends Mapper<LongWritable, Text, Text, InfoBean> {
        InfoBean bean = new InfoBean();
        Text k = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            String name = inputSplit.getPath().getName();
            String pid = "";
            /**
             * order.txt(订单表id,日期,商品编号,数量)
             * 1001	20150710	P0001	2
             * 1002	20150710	P0001	3
             * 1002	20150710	P0002	3
             * 1003	20150710	P0003	3
             * product.txt(商品编号,商品名称,价格,数量)
             * P0001	小米5	1001	2
             * P0002	锤子T1	1000	3
             * P0003	锤子	1002	4
             */
            if (name.startsWith("order")) {
                String[] fields = line.split("\t");
                pid = fields[2];
                bean.set(Integer.parseInt(fields[0]), fields[1], pid, Integer.parseInt(fields[3]), "", 0, 0, "0");
            } else {
                String[] fields = line.split("\t");
                pid = fields[0];
                bean.set(0, "", pid, 0, fields[1], Integer.parseInt(fields[2]), Float.parseFloat(fields[3]), "1");
            }
            k.set(pid);
            context.write(k, bean);
        }
    }

    static class RJoinReducer extends Reducer<Text, InfoBean, InfoBean, NullWritable> {
        @Override
        protected void  reduce(Text pid, Iterable<InfoBean> beans, Context context) throws IOException, InterruptedException {
            InfoBean pdBean = new InfoBean();
            ArrayList<InfoBean> orderBeans = new ArrayList<InfoBean>();
            for (InfoBean bean : beans) {
                if ("1".equals(bean.getFlag())) {
                    try {
                        BeanUtils.copyProperties(pdBean, bean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    InfoBean odbean = new InfoBean();
                    try {
                         BeanUtils.copyProperties(odbean, bean);
                        orderBeans.add(odbean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // 拼接两类数据形成最终结果
            for (InfoBean bean : orderBeans) {
                bean.setPname(pdBean.getPname());
                bean.setCategory_id(pdBean.getCategory_id());
                bean.setPrice(pdBean.getPrice());
                context.write(bean, NullWritable.get());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME", "sparkuser");
        Configuration conf = new Configuration();
        conf.set("mapred.textoutputformat.separator", "\t");
        Job job = Job.getInstance(conf);
        job.setJarByClass(RJoin.class);
        job.setMapperClass(RJoinMapper.class);
        job.setReducerClass(RJoinReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(InfoBean.class);

        job.setOutputKeyClass(InfoBean.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean res = job.waitForCompletion(true);
        System.exit(res ? 0 : 1);
    }
}
