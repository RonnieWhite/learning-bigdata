package practise.day17;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class mapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "white");
        map.put(2, "julia");
        map.put(3, "jones");
        /**
         Set<Integer> set = map.keySet();
         for (int key : set) {
         String value = map.get(key);
         System.out.println("键" + key + "===" + "值" + value);
         }
         **/
        Set<Map.Entry<Integer, String>> set = map.entrySet();
        for (Map.Entry<Integer, String> entry : set) {
            System.out.println(entry.getValue());
            System.out.println(entry.getKey());
        }

    }
}
