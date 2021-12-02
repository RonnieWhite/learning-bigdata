package com.baich.flink.java.custom_source;


/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-09-23
 * Time : 15:32
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MysqlBlog {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MysqlBlog(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
