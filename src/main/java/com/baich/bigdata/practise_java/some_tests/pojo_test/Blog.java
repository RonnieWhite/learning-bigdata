package com.baich.bigdata.practise_java.some_tests.pojo_test;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: Chenghui Bai
 * Date: 2020/11/13 10:20
 * project name: learning-bigdata
 * @PackgeName: com.baich.bigdata.practise_java.some_tests.pojo_test
 * @ClassName: Blog
 * @Version:
 * @Description:
 */
@Data
@Accessors(chain = true)
@ToString
public class Blog {
    private Long id;
    private String username;
    private Long createTime;
    private Long updateTime;


    public static void main(String[] args) {
        Blog blog = new Blog();
        blog.setCreateTime(new Date().getTime()).setUpdateTime(new Date().getTime());
        System.out.println(blog);
    }
}
