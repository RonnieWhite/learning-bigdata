package com.baich.learning_bigdata.learning_hadoop.cn.day08_mr.log;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GenerateLog {
    public static void main(String[] args) throws Exception {
        Logger logger = LogManager.getLogger("testlog");
        int i = 0;
        while (true) {
            logger.info(new Date().toString() + "-----------");
            i++;
            Thread.sleep(500);
            if (i > 100) {
                break;
            }
        }
    }
}
