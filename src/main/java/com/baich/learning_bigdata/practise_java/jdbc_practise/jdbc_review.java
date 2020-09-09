package com.baich.learning_bigdata.practise_java.jdbc_practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class jdbc_review {
    public static void main(String[] args) throws Exception {
        Person julia = new Person(1, "julia", 30);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/abc?characterEncoding=UTF-8", "root", "123456");
        String sql = "INSERT INTO person(id,name,age,salary) VALUES (?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, julia.getId());
        pstmt.setString(2, julia.getName());
        pstmt.setInt(3, julia.getAge());
        pstmt.setInt(4, julia.getSalary());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }


    public static class Person {
        private int id;
        private String name;
        private int age;
        private int salary;

        public Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.salary = age * 10 + 5000;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }
    }
}
