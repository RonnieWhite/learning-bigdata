package com.baich.javase.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;*/

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-18
 * Time : 09:51
 * Description:
 * Modified By:
 * version : v1.0
 */
public class TestLog {
    public static void main(String[] args) {
        TestLog testLog = new TestLog();
        testLog.test();
    }

    private Logger logger = LogManager.getLogger(this.getClass());

    private void test() {
//        Logger logger = Logger.getLogger(this.getClass());
//        LogManager.getLogger(this.getClass())
//        Log log = LogFactory.getLog(this.getClass());
        logger.info("Be a better man...");
    }


}
