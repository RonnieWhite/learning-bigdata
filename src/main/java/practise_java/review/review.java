package practise_java.review;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

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

    @Test
    public void setDemo() {
        TreeSet<String> set = new TreeSet<>();
        set.add("andy");
        set.add("julia");
        set.add("michael");
        set.add("bobo");
        NavigableSet<String> set2 = set.descendingSet();
        for (String i : set2) {
            System.out.println(i);
        }
    }

    @Test
    public void mapDemo() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "kiki");
        map.put(3, "cici");
        map.put(4, "aiai");
        map.put(2, "anan");
        NavigableMap<Integer, String> map1 = map.descendingMap();
//        Set<Map.Entry<Integer, String>> entries = map1.entrySet();
//        for (Map.Entry<Integer, String> entry : entries) {
//            System.out.println(entry.getKey() + "===" + entry.getValue());
//        }
        Iterator<Integer> iterator = map1.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void arrayToList() {
        Integer[] arr = new Integer[]{1, 3, 5, 7};
        List<Integer> list = Arrays.asList(arr);
        arr[0] = 10;
        for (int i : arr) {
            System.out.print(i + "==");
        }
        System.out.println("\n");
        for (int i : list) {
            System.out.print(i + "--");
        }

        list.remove(0);
    }

    @Test
    public void collectionDemo() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // 会报ConcurrentModificationException错
//        for (String i : list) {
//            list.remove(i);
//        }
//        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
//            String aValue = it.next();
//            if (aValue.equals("1")) {
//                it.remove();
//            }
//        }
//        for (String s : list) {
//            System.out.println(s);
//        }
//        System.out.println(list.size());
        Integer[] arr = new Integer[3];
        list.toArray(arr);
//        Integer[] arr2 = list.toArray(arr);
//        System.out.println(arr.length);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }

    @Test
    public void removeDemo() {

        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Integer> list2 = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);
        list2.add(1);
        list2.add(2);
        list.removeAll(list2);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }


}