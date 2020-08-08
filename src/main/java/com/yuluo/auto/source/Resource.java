package com.yuluo.auto.source;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description 资源加载类
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:39
 * @Version V1.0
 */
public class Resource {

    private final String[] resources = new String[]{"application.properties", "type_mapping.properties"};
    private List<Properties> list = new ArrayList<>(resources.length);

    /**
     * 加载资源文件
     */
    public void load() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream in = null;
        try {
            for (int i = 0; i < resources.length; i++) {
                in = classLoader.getResourceAsStream(resources[i]);
                Properties propert = new Properties();
                propert.load(in);
                list.add(propert);
                System.out.println("-----------> 加载： "+ resources[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("-----> 文件加载异常 <------");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
}
