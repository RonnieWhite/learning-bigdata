package com.baich.javase.always_work.nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-13
 * Time : 18:58
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Test09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chars = str.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (!list.contains(chars[i])) list.add(chars[i]);
        }
        for (char ele : list) {
            System.out.print(ele);
        }

    }
}
