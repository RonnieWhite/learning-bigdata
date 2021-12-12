package com.baich.jvm;

import java.util.ArrayList;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-07
 * Time : 14:57
 * Description:
 * Modified By:
 * version : v1.0
 * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
 * OutOfMemoryError
 */
public class HeapOOM {
    public static void main(String[] args) throws Exception {
        ArrayList<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    static class OOMObject {
    }
}
