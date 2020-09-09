package com.baich.learning_bigdata.practise_java.data_construct;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class demo {
    @Test
    public void runList() {
        List<String> list = new ArrayList<>();
        list.add("Jones");
        list.add("Andy");
        list.add("Tom");
        for (String element : list) {
            System.out.println(element);
        }
    }

    @Test
    public void runVec() {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i));
        }
    }

    @Test
    public void runLinkedList() {
        List<Long> linkedList = new LinkedList<>();
        linkedList.add(1L);
        linkedList.add(7L);
        linkedList.add(10L);
        for (long linked : linkedList) {
            System.out.println(linked);
        }
    }

    @Test
    public void runSet() {
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("1");
        treeSet.add("2");
        treeSet.add("3");
        for (String element : treeSet) {
            System.out.println(element);
        }
    }

    @Test
    public void runMap() {
        Map<Long, String> map = new TreeMap<>();
        map.put(12L, "Jones");
        map.put(13L, "Jordan");
        map.put(14L, "Jonas");
        Set<Map.Entry<Long, String>> entries = map.entrySet();
        for (Map.Entry<Long, String> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    public void runFile() throws FileNotFoundException, IOException {
        File file = new File("E:/a.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        br.readLine();
    }

    public String runTest(ArrayList<String> arrayList) {
        while (true) {
            if (arrayList.size() == 2) {
                return "1";
            }
            arrayList.remove(arrayList.size() - 1);
        }
    }

    public String runT2() {
        int a = 1;
        int b = 2;
        if (a != b) {
            return "1";
        }
        return "2";
    }

    @Test
    public void run() {
        System.out.println(runT2());
    }


    @Test
    public void runTo() {
        int[] args = {1, 2, 3, 4};
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(runTest(list));
    }
}




