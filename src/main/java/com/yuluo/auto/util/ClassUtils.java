package com.yuluo.auto.util;

import java.io.File;
import java.net.URL;

/**
 * @Description 获取类加载器
 * @Author 蚂蚁不是ant
 * @Date 2020/8/7 22:29
 * @Version V1.0
 */
public abstract class ClassUtils {

    /**
     * 获取类加载
     * @return
     */
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null){
            classLoader = ClassUtils.class.getClassLoader();
            if (classLoader == null){
                classLoader = ClassUtils.class.getClassLoader();
                if (classLoader == null){
                    classLoader = ClassLoader.getSystemClassLoader();
                }
            }
        }
        return classLoader;
    }

    /**
     * 获取模板文件
     * @return
     */
    public static File  getResourceFile(){
        URL url = getDefaultClassLoader().getResource("templates");
        return new File(url.getFile());
    }
}
