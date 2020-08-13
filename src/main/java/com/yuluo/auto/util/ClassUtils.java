package com.yuluo.auto.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.yuluo.auto.constants.Constant.TEMPLATES_FILE;

/**
 * @Description 获取类加载器
 * @Author 蚂蚁不是ant
 * @Date 2020/8/7 22:29
 * @Version V1.0
 */
public abstract class ClassUtils {

    private static final Logger log = Logger.getLogger(ClassUtils.class);

    private static String TEMPLATES = "templates";

    private static String TEMPLATE_DIR = "";

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
    public static File getResourceFile() throws IOException {
        String file = getResourcePath();
        return new File(file);
    }

    private static String getResourcePath() throws IOException {
        String template = getDefaultClassLoader().getResource(TEMPLATES).getFile();
        String jar = "jar";
        if (template.contains(jar)) {
            String base = System.getProperty("user.dir");
            TEMPLATE_DIR = base + File.separator + TEMPLATES + File.separator;
            mkdir();
            copyTemplates();
            template = TEMPLATE_DIR;
        }
        return template;
    }

    private static void copyTemplates() throws IOException {
        InputStream in = null;
        BufferedReader reader = null;
        BufferedWriter osw = null;
        for (String name : TEMPLATES_FILE) {
            in = getClassLoaderAsStream(TEMPLATES + "/" + name);
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            osw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TEMPLATE_DIR + name), StandardCharsets.UTF_8));
            String line = reader.readLine();
            while (line != null) {
                osw.write(line);
                osw.newLine();
                line = reader.readLine();
            }
            osw.flush();
            osw.close();
            reader.close();
        }
    }


    public static void mkdir() {
        File file = new File(TEMPLATE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    public static boolean delete(String name) {
        File file = new File(name);
        if (file.isDirectory()) {
            String[] files = file.list();
            for (String f : files) {
                delete(name + f);
            }
        }
       return file.delete();
    }

    public static void delete() {
        boolean flag = delete(TEMPLATE_DIR);
        if (flag){
            log.warn("delete success ... ");
        }
    }
}
