package com.baich.bigdata.practise_java.jdbc_practise;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbc_test {
    public static void main(String[] args) throws Exception {
//        Connection conn = null;
//        conn = getConnection();
//        Statement s = conn.createStatement();
//        String sql = "CREATE TABLE t_test(id INT, name VARCHAR(20))";
//        s.execute(sql);
//        System.out.println("执行成功！");
        // PreparedStatement 占位符 添加一条记录
//        int id = 1;
//        String name = "andy";
//        addRecord(id, name);
        // PreparedStatement 占位符 批量添加记录
//        int[] ids = {2, 3, 4, 5, 6, 7, 8, 9, 10};
//        String[] names = {"Jordan", "James", "Bryant", "Julia", "Jones", "Carter", "Andrew", "Curry", "Johnson", "Bob"};
//        addRecordBatch(ids, names);
        // PreparedStatement查询
        List<People> peopleList = null;
        peopleList = queryPeople();
        for (People people : peopleList) {
            System.out.println(people);
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 初始化驱动类com.mysql.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/abc?characterEncoding=UTF-8", "root", "123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void addRecord(int id, String name) {
        String sql = "INSERT INTO t_test(id, name) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addRecordBatch(int[] ids, String[] names) {
        String sql = "INSERT INTO t_test(id, name) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < ids.length; i++) {
                pstmt.setInt(1, ids[i]);
                pstmt.setString(2, names[i]);
                pstmt.addBatch();
                if (i % 3 == 0) {
                    pstmt.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<People> queryPeople() {
        String sql = "SELECT id, name FROM t_test";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<People> peopleList = new ArrayList<>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                People people = new People();
                people.setId(id);
                people.setName(name);
                peopleList.add(people);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return peopleList;
    }


}
