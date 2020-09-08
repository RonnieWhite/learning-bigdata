package com.white.learning_bigdata.practise_java.lesson.day01;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.ArrayList;

public class RightJoin {
    public static class RightJoinMapper extends Mapper<LongWritable, Text, Text, JoinBean> {
        JoinBean bean = new JoinBean();
        Text k = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            String name = inputSplit.getPath().getName();
            String product_id = "";
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
            String[] fields = line.split("\t");
            if (name.startsWith("order")) {
                product_id = fields[2];
                bean.set(Integer.parseInt(fields[0]), fields[1], product_id, Integer.parseInt(fields[3]), "", 0, 0, "0");
            } else {
                product_id = fields[0];
                bean.set(0, "", product_id, 0, fields[1], Integer.parseInt(fields[2]), Float.parseFloat(fields[3]), "1");
            }
            k.set(product_id);
            context.write(k, bean);
        }
    }

    public static class RightJoinReducer extends Reducer<Text, JoinBean, JoinBean, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<JoinBean> values, Context context) throws IOException, InterruptedException {
            JoinBean pdBean = new JoinBean();
            ArrayList<JoinBean> orderBean = new ArrayList<>();
            for (JoinBean bean : values) {
                if ("1".equals(bean.getFlag())) {
                    try {
                        BeanUtils.copyProperties(pdBean, bean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JoinBean odBean = new JoinBean();
                    try {
                        BeanUtils.copyProperties(odBean, bean);
                        orderBean.add(odBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for (JoinBean bean : orderBean) {
                bean.setProduct_name(pdBean.getProduct_name());
                bean.setCategory_id(pdBean.getCategory_id());
                bean.setPrice(pdBean.getPrice());
                context.write(bean, NullWritable.get());
            }
        }
    }
}
