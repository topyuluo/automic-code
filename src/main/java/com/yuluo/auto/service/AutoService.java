package com.yuluo.auto.service;

import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import com.yuluo.auto.source.BaseResource;
import com.yuluo.auto.util.ClassUtils;
import com.yuluo.auto.util.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description 程序入口
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:29
 * @Version V1.0
 */
public class AutoService extends BaseResource {

    private static Logger log = Logger.getLogger(AutoService.class);

    private static AutoService service = new AutoService();
    private String[] arrays = new String[]{"db.url", "db.username", "db.password", "base.package", "path"};

    private DbService dbService = null;

    private AutoService() {
        this.dbService = new DbService(this);
    }

    public static AutoService getInstance() {
        return service;
    }

    public void doMain(String[] args) {
        //加载资源
        load(args);
        try {
            dbService.process();
        } catch (MySQLTimeoutException | IOException e) {
            log.error(e);
        }
        ClassUtils.delete();
    }

    /**
     * 加载配置资源
     *
     * @param args
     */
    private void load(String[] args) {
        String userDir = null;
        Object obj = null;
        int length = 5;
        if (StringUtils.isNotEmpty(args)) {
            if (args.length != length) {
                throw new IllegalArgumentException("命令行需要输入5个参数！");
            }
            Map<String, String> map = new HashMap<>(16);
            for (int i = 0; i < arrays.length; i++) {
                map.put(arrays[i], args[i]);
            }
            obj = map;
        } else if (!"".equals(userDir = judgeConfig())) {
            obj = userDir;
        }
        loadResource(obj);
    }

    public static String judgeConfig() {
        log.warn("尝试从外部配置文件加载配置 .... ");
        String[] files = new String[]{"application.properties", "conf" + File.separator + "application.properties"};
        String userDir = System.getProperty("user.dir");
        File f = null;
        for (String file : files) {
            f = getFile(userDir, file);
            if (f.exists()) {
                log.warn("存在外部文件, 加载到程序中 ... ");
                return f.getAbsolutePath();
            }
        }
        log.warn("无外部配置文件，若配置请检查路径及文件名：application.properties");
        return "";
    }

    public static File getFile(String baseDir, String fileName) {
        String name = baseDir + File.separator + fileName;
        return new File(name);
    }

    @Override
    protected void loadMapResource(Map<String, String> map) {
        Properties properties = getProperties();
        properties.putAll(map);
        addResource(properties);
    }

    @Override
    protected void loadUserFileResource(String userDir) throws IOException {
        log.info("load user file , dir : " + userDir);
        try (InputStream in = new FileInputStream(userDir)) {
            Properties properties = getProperties();
            properties.load(in);
            addResource(properties);
        }
        log.info(userDir + "load file success .... ");
    }
}
