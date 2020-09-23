package com.baich.bigdata.learning_flink_java.custom_source;

import org.apache.flink.api.common.io.InputFormat;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.InputFormatSourceFunction;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-09-23
 * Time : 17:40
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MysqlInputFormatSourceFunc extends InputFormatSourceFunction<MysqlBlog> {
    public MysqlInputFormatSourceFunc(InputFormat<MysqlBlog, ?> format, TypeInformation<MysqlBlog> typeInfo) {
        super(format, typeInfo);
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
    }
}
