package main.java.practise.review;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class review {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(6);
        list.add(5);
        list.add(5);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1.equals(o2)) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        for (int i : list) {
            System.out.println(i);
        }
    }

    @Test
    public void runHash() {
        String a = "abc";
        int code = a.hashCode();
        int hashCode = code ^ (code >>> 16);
        System.out.println(code);
        System.out.println(hashCode);
    }

    @Test
    public void runW() {
        System.out.println(17 % 3);
    }

    @Test
    public void hash() {
        new HashSet<>();
        int h;
        String key = "white";
        h = key.hashCode();
        int s = h >>> 16;
        int c = (key == null) ? 0 : h ^ s;
        int i = 2 & 3;
        System.out.println(i);
//        System.out.println(c);
//        System.out.println(h);
//        System.out.println(s);
    }
}
