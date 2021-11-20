package com.baich.bigdata.phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-02
 * Time : 10:43
 * Description: 样例，演示使用Phoenix批量插入数据
 * Modified By:
 * version : v1.0
 */
public class PhoenixDemo {

    static List<PhoenixPerson> phoenixPersonList = new ArrayList<>();

    static {
        phoenixPersonList.add(new PhoenixPerson(1, "Jordan", 23355.3d));
        phoenixPersonList.add(new PhoenixPerson(2, "Julia", 20064.5d));
        phoenixPersonList.add(new PhoenixPerson(3, "Judy", 2123.3d));
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty("phoenix.schema.isNamespaceMappingEnabled", "true");
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        Connection connection = DriverManager.getConnection("jdbc:phoenix:zkHost1,zkHost2,zkHost3,zkHost4,zkHost5:2181:/hbase-unsecure", props);
        String sql = "UPSERT INTO db.table VALUES(?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        for (PhoenixPerson phoenixPerson : phoenixPersonList) {
            pstmt.setInt(1, phoenixPerson.getId());
            pstmt.setString(2, phoenixPerson.getName());
            pstmt.setDouble(3, phoenixPerson.getSalary());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        connection.commit();
        pstmt.close();
        connection.close();

    }

    static class PhoenixPerson {
        private Integer id;
        private String name;
        private Double salary;


        public PhoenixPerson(Integer id, String name, Double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "PhoenixPerson{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }
    }
}
