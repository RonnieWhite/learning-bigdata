package practise_java.day01;

import java.util.ArrayList;
import java.util.List;

public class IterateTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // 普通for循环遍历
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        // 迭代器循环遍历
//        Iterator it = list.iterator();
//        while (it.hasNext()) {
//            System.out.print(it.next().toString() + ',');
//        }
        // 增强for循环遍历
        for (Integer i : list) {
            System.out.print(i + ',');
        }
    }
}