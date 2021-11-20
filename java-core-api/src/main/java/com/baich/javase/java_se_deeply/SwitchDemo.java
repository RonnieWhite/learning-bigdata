package com.baich.javase.java_se_deeply;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-09-17
 * Time : 16:51
 * Description:
 * Modified By:
 * version : v1.0
 */
public class SwitchDemo {
    public static void main(String[] args) {
        SwitchDemo demo = new SwitchDemo();
        demo.xxx(1);
    }

    public void xxx(int id) {
        switch (id) {
            case 0:
                System.out.println("0");
            case 1:
            case 2:
                System.out.println("1或者2");
                break;
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("其它");
        }
    }
}
