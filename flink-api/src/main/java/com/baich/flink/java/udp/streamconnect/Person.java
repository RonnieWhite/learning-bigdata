package com.baich.flink.java.udp.streamconnect;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-08
 * Time : 19:58
 * Description: 实体类
 * Modified By:
 * version : v1.0
 */
public class Person {
    private String userId;
    private String userName;
    private Integer age;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(String userId, String userName, Integer age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
