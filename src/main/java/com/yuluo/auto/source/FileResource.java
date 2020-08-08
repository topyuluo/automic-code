package com.yuluo.auto.source;

import com.yuluo.auto.freemark.FreeMarkerConfig;
import com.yuluo.auto.model.Table;
import com.yuluo.auto.util.Assert;
import com.yuluo.auto.util.ClassUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description 加载文件模板资源
 * @Author 蚂蚁不是ant
 * @Date 2020/8/6 22:28
 * @Version V1.0
 */
public class FileResource {

    /**
     * 加载模板信息
     */
    public void loadTemplate(Table table) {
        File file = ClassUtils.getResourceFile();
        Configuration config = FreeMarkerConfig.getInstance(file);
        doProcess(table, file, config);
    }

    private void doProcess(Table table, File file, Configuration config) {
        Assert.notNull(file, "file is require not null !");
        for (File f : file.listFiles()) {
            if (f.getName().startsWith("Annotion")) {
                continue;
            }
            // 第四步：加载一个模板，创建一个模板对象。
            Template template = null;
            Writer out = null;
            try {
                String fileName = f.getName();
                template = config.getTemplate(fileName);
                System.out.println("-----------> 加载模板" + fileName);
                String name = getFilePath(table, fileName);
                out = new FileWriter(name);
                // 第七步：调用模板对象的process方法输出文件。
                //向数据集中添加数据
                template.process(table, out);
                System.out.println("-----------> 生成文件 ：" + name);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 第八步：关闭流。
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private String getFilePath(Table table, String fileName) {
        String name = "D:/autocode/";
        String prefix = fileName.substring(0, fileName.indexOf("."));

        if (prefix.toLowerCase().contains("controller")) {
            name += "controller/";
        }
        if (prefix.toLowerCase().contains("service")) {
            name += "service/";
        }
        if (prefix.toLowerCase().contains("impl")) {
            name += "impl/";
        }
        if (prefix.toLowerCase().contains("dao")) {
            name += "dao/";
        }
        if (prefix.toLowerCase().contains("io") || prefix.toLowerCase().contains("vo")) {
            name += "controller/vo/";
        }
        if (prefix.toLowerCase().contains("mapper")) {
            name += "mapper/";
        }
        if (prefix.toLowerCase().contains("model")) {
            name += "model/";
        }
        File file = new File(name);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (prefix.toLowerCase().contains("model")) {
            name += table.getUpperCaseName();
        } else {
            name += table.getUpperCaseName() + fileName.substring(0, fileName.indexOf("."));
        }

        if (fileName.contains("Mapper")) {
            name += ".xml";
        } else {
            name += ".java";
        }
        return name;
    }

    public static void main(String[] args) {
        Table table = new Table("User", "用户表");
        new FileResource().loadTemplate(table);
    }

}
