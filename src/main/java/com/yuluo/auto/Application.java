package com.yuluo.auto;

import com.yuluo.auto.db.DBUtil;
import com.yuluo.auto.util.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.time.OffsetDateTime;
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
        String[] conf = null;
        if (StringUtils.isEmpty(args)) {
            log.info("输入参数的个数：" + args.length);
            if (args.length == 5) {
                HashMap<String, String> map = new HashMap<>();
                map.put("db.url", args[0]);
                map.put("db.username", args[1]);
                map.put("db.password", args[2]);
                map.put("base.package", args[3]);
                map.put("path", args[4]);
                start(map);
            }
        } else if ((conf =judgeConfig()).length != 0) {
            System.out.println("=====================");
            start(conf[0]);
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

    private static void start(String path){
        DBUtil.getInstance().load(path);
    }

    public static String[] judgeConfig() {
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        String dir = userDir + File.separator + "jdbc.properties";
        System.out.println(userDir);
        File file = getFile(dir);
        if (file.exists()) {
            System.out.println(userDir + "adfdsff");
            return new String[]{dir};
        }
        dir = userDir + File.separator + "conf" + File.separator + "jdbc.properties";
        System.out.println(dir);
        file = getFile(dir);
        if (file.exists()) {
            System.out.println("_______________________");
            return new String[]{dir};
        }
        return new String[0];
    }

    public static File getFile(String path) {
        return new File(path);
    }
}
