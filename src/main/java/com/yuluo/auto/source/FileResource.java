package com.yuluo.auto.source;

import com.yuluo.auto.freemark.FreeMarkerConfig;
import com.yuluo.auto.model.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

import static com.yuluo.auto.constants.Constant.*;

/**
 * @Description 加载文件模板资源
 * @Author 蚂蚁不是ant
 * @Date 2020/8/6 22:28
 * @Version V1.0
 */
public class FileResource {

    private static Logger log = Logger.getLogger(FileResource.class);

    private Resource resource;

    public FileResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * 加载模板信息
     */
    public void loadTemplate(Table table) {
        Configuration config = FreeMarkerConfig.getInstance();
        doProcess(table ,config);
    }

    /**
     * 流程处理
     *
     * @param table
     * @param config
     */
    private void doProcess(Table table , Configuration config) {
        try {
            for (File f : Objects.requireNonNull(FreeMarkerConfig.getResourceFile().listFiles())) {
                createFile(table, config, f);
            }
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据模板创建文件
     *
     * @param table
     * @param config
     * @param f
     * @throws IOException
     * @throws TemplateException
     */
    private void createFile(Table table, Configuration config, File f) throws IOException, TemplateException {
        String name = f.getName();
        Template template = config.getTemplate(name);
        log.info("load template- " + name.substring(0 ,name.indexOf(".")));
        name = getFilePath(table.getUpperCaseName(), name);
        try (Writer out = new FileWriter(name)) {
            template.process(table, out);
        }
        log.info("create file- " + name);
    }

    /**
     * 获取文件路径
     * @param content
     * @param fileName
     * @return
     */
    private String getFilePath(String content, String fileName) {
        StringBuilder sb = handlePath(resource.getApplictionProperty(PATH) , fileName);
        mkdirs(sb);
        String prefix = fileName.substring(0, fileName.indexOf("."));
        if (prefix.toLowerCase().contains(MAPPER)) {
            sb.append(content);
        } else {
            sb.append(content).append(fileName, 0, fileName.indexOf("."));
        }
        if (fileName.contains("Mapper")) {
            sb.append(".xml");
        } else {
            sb.append(".java");
        }
        return sb.toString();
    }

    /**
     * 处理文件路径
     * @param basePath
     * @return
     */
    private StringBuilder handlePath(String basePath , String fileName) {
        StringBuilder sb = new StringBuilder(basePath);
        if (basePath.contains("/")) {
            basePath = basePath.replaceAll("/", File.separator);
        }
        if (!basePath.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append(FILE_MAPPING.get(fileName)).append(File.separator);
        return sb ;
    }

    /**
     * 文件夹不存在则创建
     * @param sb
     */
    private void mkdirs(StringBuilder sb) {
        File file = new File(sb.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
