package com.baich.bigdata.javase.java_se_deeply;

import org.junit.Test;

import java.util.*;

public class demo01 {
    @Test
    public void listDemo() {
        List<Integer> list = new ArrayList<Integer>(1);
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);
//        Collections.sort(list);
//        for (int i : list) {
//            System.out.println(i);
//        }
//        list.remove(2);
//        System.out.println(list.size() + list.get(2));
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void arraDemo() {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        Arrays.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void mapDemo() {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(3, "ronnie");
        map.put(2, "white");
        map.put(1, "gigi");
        map.put(4, "andy");
//        map.comparator();
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println(entry.getKey() + "==" + entry.getValue());
        }
    }

}
