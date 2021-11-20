package com.baich.javase.java_se_deeply.god_of_bigdata;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-10-19
 * Time : 10:59
 * Description: 测试，在不添加元素是，数组容量为0，当给数组添加一个元素后，容量变为10
 * Modified By:
 * version : v1.0
 */
public class ArrayListElementDataDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int capacity = getArrayListCapacity(list);
        System.out.println(capacity);
    }

    private static int getArrayListCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] objects = (Object[]) field.get(arrayList);
            return objects.length;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
