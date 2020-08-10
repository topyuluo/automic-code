package com.yuluo.auto.source;

import com.yuluo.auto.util.Assert;
import com.yuluo.auto.util.ClassUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description 资源加载类
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:39
 * @Version V1.0
 */
public class Resource {

    private static Logger log = Logger.getLogger(Resource.class);

    private final String[] resources = new String[]{"application.properties", "type_mapping.properties"};
    private List<Properties> list = new ArrayList<>(resources.length);

    /**
     * 加载资源文件
     */
    public void load() {
        loadFile(resources);
    }

    public void load(Map<String, String> map) {
        loadMap(map);
        loadFile(resources[1]);
        log.info("load resource-" + resources[1]);
    }

    public void load(String path) {
        loadConfFile(path);
        loadFile(resources[1]);
        log.info("load resource-" + resources[1]);
    }

    private void loadConfFile(String path) {
        try {

            InputStream in = new FileInputStream(new File(path));
            Properties properties = new Properties();
            properties.load(in);
            list.add(properties);
            log.info("load conf file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取资源文件
     *
     * @return
     */
    public Properties getResource(int index) {
        return list.get(index);
    }

    /**
     * 获取第二个文件资源中的属性
     *
     * @param key
     * @return
     */
    public String getTypeMappingProperty(String key) {
        Assert.notEmpty(key, "property is null !");
        return list.get(1).getProperty(key);
    }

    /**
     * 获取第一个文件资源中的属性
     *
     * @param key
     * @return
     */
    public String getApplictionProperty(String key) {
        Assert.notEmpty(key, "property is null ");
        return list.get(0).getProperty(key);
    }


    /**
     * 加载命令行输入的参数
     *
     * @param map
     */
    private void loadMap(Map<String, String> map) {
        Properties propert = new Properties();
        map.forEach(propert::setProperty);
        list.add(propert);
        log.info("user input params :" + map);
    }

    private void loadFile(String... files) {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        InputStream in = null;
        Properties properties = null;
        try {
            for (String file : files) {
                in = classLoader.getResourceAsStream(file);
                properties = getProperties();
                properties.load(in);
                list.add(properties);
                log.info("load file resource : " + file);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("load resource file exception :" + e.getMessage());
                }
            }
        }
    }

    private Properties getProperties() {
        return new Properties();
    }

}
