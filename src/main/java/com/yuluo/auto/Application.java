package com.yuluo.auto;

import com.yuluo.auto.db.DBUtil;
import com.yuluo.auto.util.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;

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
        if (StringUtils.isEmpty(args)) {
            if (args.length == 5) {
                HashMap<String, String> map = new HashMap<>();
                map.put("db.url", args[0]);
                map.put("db.username", args[1]);
                map.put("db.password", args[2]);
                map.put("base.package", args[3]);
                map.put("path", args[4]);
                start(map);
            }
        } else {
            start();
        }
        log.info("success .... ");
    }

    private static void start(HashMap<String, String> map) {
        DBUtil.getInstance().load(map);
    }

    private static void start() {
        DBUtil.getInstance().init();
    }
}
