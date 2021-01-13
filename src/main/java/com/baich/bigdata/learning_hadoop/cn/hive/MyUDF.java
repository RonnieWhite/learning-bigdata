package com.baich.bigdata.learning_hadoop.cn.hive;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @Author: Chenghui Bai
 * Date: 2020/11/24 15:54
 * project name: learning-bigdata
 * @PackgeName: com.baich.bigdata.learning_hadoop.cn.hive
 * @ClassName: MyUDF
 * @Version:
 * @Description:
 */
public class MyUDF extends UDF {
    public String evaluate(String input){
        return "Hello: "+input;
    }

    public static void main(String[] args) {
        MyUDF udf = new MyUDF();
        String evaluate = udf.evaluate("baich");
        System.out.println(evaluate);
    }
}
