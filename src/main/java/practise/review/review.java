package main.java.practise.review;

import java.util.ArrayList;
import java.util.Comparator;

public class review {
    static {
        System.out.println("hello java");
    }

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
}
