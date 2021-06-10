package com.baich.bigdata.practise_java.always_work;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String hexStr = sc.nextLine();

            String s = Integer.valueOf(hexStr.substring(2), 16).toString();
            System.out.println(s);

//            System.out.println(Integer.valueOf(hexStr.substring(2),16).toString());
        }
    }
}
