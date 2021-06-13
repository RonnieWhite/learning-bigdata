package com.baich.bigdata.practise_java.always_work.nowcoder;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-13
 * Time : 16:56
 * Description:数据表记录包含表索引和数值（int范围的正整数），请对表索引相同的记录进行合并，
 * 即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 * 输入描述：
 * 先输入键值对的个数
 * 然后输入成对的index和value值，以空格隔开
 * <p>
 * 输出描述：
 * 输出合并后的键值对（多行）
 * <p>
 * Modified By:
 * version : v1.0
 */
public class Test08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int count = sc.nextInt();
            TreeMap<Integer, Integer> map = new TreeMap<>();

            while (count >= 0) {
                int key = sc.nextInt();
                int value = sc.nextInt();
                if (map.containsKey(key)) {
                    value += map.get(key);
                }
                map.put(key, value);
                count--;
            }
            Set<Integer> keySet = map.keySet();
            for (Integer key : keySet) {
                System.out.println(key + " " + map.get(key));
            }
        }
    }
}
