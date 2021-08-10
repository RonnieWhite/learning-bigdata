package com.baich.bigdata.javase.always_work;


import java.util.*;

public class Map2List {
    public static void main(String[] args) {
        Map<Integer, Student> map = new HashMap<>();
        map.put(1, new Student(1L, "Ronnie"));
        map.put(2, new Student(2L, "Judy"));
        List<Student> studentList = new ArrayList<>(map.values());
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
