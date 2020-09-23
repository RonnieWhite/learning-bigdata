package com.baich.bigdata.learning_flink_java.custom_source;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-09-23
 * Time : 15:32
 * Description:
 * Modified By:
 * version : v1.0
 */
@Data
@AllArgsConstructor
public class MysqlBlog {
    private Long id;
    private String title;
}
