package com.yuluo.auto;

import com.yuluo.auto.service.AutoService;
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
        long start = System.currentTimeMillis();

        AutoService.getInstance().doMain(args);

        long end = System.currentTimeMillis();
        log.info("success .... 耗时：" + (end - start) / 1000 + "s");
    }
}
