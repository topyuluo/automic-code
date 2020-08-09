package com.yuluo.auto.util;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.jar.JarFile;

import static com.yuluo.auto.constants.Constant.TEMPLATES_FILE;

/**
 * @Description 获取类加载器
 * @Author 蚂蚁不是ant
 * @Date 2020/8/7 22:29
 * @Version V1.0
 */
public abstract class ClassUtils {

    /**
     * 获取类加载
     *
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = ClassUtils.class.getClassLoader();
            if (classLoader == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }

    /**
     * 获取文件流
     *
     * @param source
     * @return
     */
    public static InputStream getClassLoaderAsStream(String source) {
        return getDefaultClassLoader().getResourceAsStream(source);
    }

    /**
     * 获取模板文件
     *
     * @return
     */
    public static File getResourceFile() {
        String file = getResourcePath();
        return new File(file);
    }

    private static String getResourcePath() {
        String template = getDefaultClassLoader().getResource("templates").getFile();
        if (template.contains("jar")) {
            String base = System.getProperty("user.dir");
            String newPath = base + File.separator + "templates" + File.separator;
            mkdir(newPath);
            for (String name : TEMPLATES_FILE) {
                InputStream in = getDefaultClassLoader().getResourceAsStream("templates/" + name);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(in , "UTF-8") );
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    BufferedWriter  osw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newPath + name), "UTF-8"));
                    String line = reader.readLine();
                    while (line != null) {
                        osw.write(line);
                        osw.newLine();
                        line = reader.readLine();
                    }
                    osw.flush();
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            template = newPath;
        }
        return template;
    }

    public static void mkdir(String base) {
        File file = new File(base);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
