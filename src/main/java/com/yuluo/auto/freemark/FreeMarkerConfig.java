package com.yuluo.auto.freemark;

import com.yuluo.auto.util.ClassUtils;
import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * @Description freemark 解析模板类
 * @Author 蚂蚁不是ant
 * @Date 2020/8/7 22:37
 * @Version V1.0
 */
public class FreeMarkerConfig {
    private static File file = null;

    public static Configuration getInstance() throws IOException {

        Configuration config = new Configuration(Configuration.VERSION_2_3_30);
        FreeMarkerConfig.file = ClassUtils.getResourceFile();
        config.setDefaultEncoding("UTF-8");
        try {
            config.setDirectoryForTemplateLoading(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static File getResourceFile() {
        return file;
    }

}
