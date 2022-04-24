package com.baich.javase.lambda_demo;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2022-04-21
 * Time : 14:37
 * Description:
 * Modified By:
 * version : v1.0
 */
@FunctionalInterface
public interface MathOperation {
    int operation(int a, int b);

    default int reduce(int a, int b) {
        return 1;
    }

    ;
}
