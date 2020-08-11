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
public abstract class BaseResource {

    private static Logger log = Logger.getLogger(BaseResource.class);

    private final String[] resources = new String[]{"application.properties", "type_mapping.properties"};
    private final List<Properties> propertiesList = new ArrayList<>(resources.length);

    /**
     * 加载命令行参数
     * @param obj
     */
    protected void loadResource(Object obj) {
        load();
        if (obj instanceof Map) {
            loadMapResource((Map<String, String>) obj);
        }
        if (obj instanceof String) {
            try {
                loadUserFileResource((String) obj);
            } catch (IOException e) {
                log.error(e);
                log.info("加载用户配置的资源文件异常！");
            }
        }
        log.info("all file resource load success ....... ");
    }

    /**
     * 加载资源文件
     */
    public void load() {
        loadFile(resources);
    }

    /**
     * 获取资源文件
     *
     * @return
     */
    public Properties getResource(int index) {
        return propertiesList.get(index);
    }

    /**
     * 获取第二个文件资源中的属性
     *
     * @param key
     * @return
     */
    public String getTypeMappingProperty(String key) {
        Assert.notEmpty(key, "property is null !");
        return propertiesList.get(1).getProperty(key);
    }

    /**
     * 获取第一个文件资源中的属性
     *
     * @param key
     * @return
     */
    public String getApplictionProperty(String key) {
        Assert.notEmpty(key, "property is null ");
        return propertiesList.get(0).getProperty(key);
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
                this.propertiesList.add(properties);
                log.info("load file resource : " + file);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            closeInputStream(in);
        }
    }

    public void closeInputStream(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("load resource file exception :" + e.getMessage());
            }
        }
    }

    protected Properties getProperties() {
        return new Properties();
    }

    protected void addResource(Properties properties) {
        this.propertiesList.set(0, properties);
    }

    /**
     * 加载map 资源
     *
     * @param map
     */
    protected abstract void loadMapResource(Map<String, String> map);

    /**
     * 加载用户自定义文件资源
     *
     * @param userDir
     * @throws IOException
     */
    protected abstract void loadUserFileResource(String userDir) throws IOException;
}
