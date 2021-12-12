package com.baich.jvm;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-07
 * Time : 22:22
 * Description:
 * Modified By:
 * version : v1.0
 * VM Args: -Xss128k
 * StackOverflowError
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
