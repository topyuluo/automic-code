package com.yuluo.auto;

import com.yuluo.auto.db.DBUtil;
import org.apache.log4j.Logger;

/**
 * @Description 程序入口
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:29
 * @Version V1.0
 */
public class Application {
    private static Logger log = Logger.getLogger(Application.class);
    public static void main(String[] args) {
        log.info("application start ... ");
        start();
        log.info("success .... ");
    }

    private static void start(){
        DBUtil.getInstance().init();
    }
}
