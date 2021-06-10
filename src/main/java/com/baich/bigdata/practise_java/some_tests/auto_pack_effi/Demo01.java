package com.baich.bigdata.practise_java.some_tests.auto_pack_effi;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-08
 * Time : 21:45
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Demo01 {
    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
        demoClass.method01long();
        demoClass.method02Long();
    }


    static class DemoClass {
        public DemoClass() {
        }

        void method01long() {
            long startTime = System.currentTimeMillis();
            long sum = 0;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sum += i;
            }
            System.out.println(sum);
            long endTime = System.currentTimeMillis();
            System.out.printf("消耗时间：%d毫秒", (endTime - startTime));
            System.out.println();

        }

        void method02Long() {
            long startTime = System.currentTimeMillis();
            Long sum = 0L;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sum += i;
            }
            System.out.println(sum);
            long endTime = System.currentTimeMillis();
            System.out.printf("消耗时间：%d毫秒", (endTime - startTime));

        }
    }
}
