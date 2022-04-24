package com.baich.javase.lambda_demo;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2022-04-21
 * Time : 14:33
 * Description:
 * Modified By:
 * version : v1.0
 */
public class LambdaDemo1 {
    public static void main(String[] args) {
/*        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.forEach(System.out::println);*/
        MathOperation mathOperation = (x, y) -> x + y;
        int i = mathOperation.operation(1, 2);
//        int i = mathOperation.reduce(1, 2);
        System.out.println(i);
    }
}
