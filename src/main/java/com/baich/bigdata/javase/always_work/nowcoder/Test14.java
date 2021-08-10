package com.baich.bigdata.javase.always_work.nowcoder;


import java.util.Scanner;
import java.util.TreeSet;

/**
 * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
 */
public class Test14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int count = sc.nextInt();
            TreeSet<String> treeSet = new TreeSet<>();
            for (int i=0;i<=count;i++){
                String str = sc.nextLine();
                treeSet.add(str);
            }
            for (String ele:treeSet){
                System.out.println(ele);
            }
        }

    }
}

