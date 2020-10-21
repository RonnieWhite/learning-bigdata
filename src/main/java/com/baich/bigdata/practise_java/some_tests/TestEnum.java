package com.baich.bigdata.practise_java.some_tests;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-10-15
 * Time : 15:30
 * Description:
 * Modified By:
 * version : v1.0
 */
public class TestEnum {
    public static void main(String[] args) {
        DbType dbType = DbType.valueOf("Oracle");
        System.out.println(dbType.equals(DbType.MySQL));
    }
}
