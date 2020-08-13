package com.yuluo.auto.source;

import com.yuluo.auto.freemark.FreeMarkerConfig;
import com.yuluo.auto.model.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
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

    private BaseResource resource;
    private List<Table> tables;

    public FileResource(BaseResource resource, List<Table> tables) {
        this.resource = resource;
        this.tables = tables;
    }

    /**
     * 加载模板信息
     */
    public void loadTemplate() throws IOException {
        Configuration config = FreeMarkerConfig.getInstance();
//        tables.forEach(t -> doProcess(t , config));
        doProcess(config);
    }

    /**
     * 流程处理
     *
     * @param config
     */
    private void doProcess(Configuration config) {
        tables.forEach(t -> {
            for (File f : Objects.requireNonNull(FreeMarkerConfig.getResourceFile().listFiles())) {
                try {
                    createFile(t, config, f.getName());
                } catch (IOException | TemplateException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 根据模板创建文件
     *
     * @param table
     * @param config
     * @throws IOException
     * @throws TemplateException
     */
    private void createFile(Table table, Configuration config, String name) throws IOException, TemplateException {
        Template template = config.getTemplate(name);
        log.warn("load template - " + name.substring(0, name.indexOf(".")));
        name = getFilePath(table.getUpperCaseName(), name);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"));) {
            template.process(table, writer);
        }
        log.info("create file - " + name);
    }

    /**
     * 获取文件路径
     *
     * @param content
     * @param fileName
     * @return
     */
    private String getFilePath(String content, String fileName) {

        StringBuilder sb = handlePath(getPath(fileName), fileName);
        mkdirs(sb);
        String prefix = fileName.substring(0, fileName.indexOf("."));
        String mapper = "Mapper";
        if (prefix.toLowerCase().contains(MODEL)) {
            sb.append(content);
        } else if (prefix.toLowerCase().startsWith("mybatis")) {
            sb.append(fileName, 0, fileName.indexOf("."));
        } else {
            sb.append(content).append(fileName, 0, fileName.indexOf("."));
        }
        if (fileName.startsWith(mapper)) {
            sb.append(".xml");
        } else {
            sb.append(".java");
        }
        return sb.toString();
    }

    private String getPath(String fileName) {
        String pathName = "path." + (fileName.substring(0, fileName.indexOf(".")).toLowerCase());
        String path = resource.getApplictionProperty(pathName);
        if (null == path || "".equals(path)) {
            path = resource.getApplictionProperty(PATH);
            if (null == path || "".equals(path)) {
                path = System.getProperty("user.dir");
            }
        }
        return path;
    }

    /**
     * 处理文件路径
     *
     * @param basePath
     * @return
     */
    private StringBuilder handlePath(String basePath, String fileName) {
        StringBuilder sb = new StringBuilder(basePath);
        String backslash = "/";
        if (basePath.contains(backslash)) {
            basePath = basePath.replaceAll(backslash, File.separator);
        }
        if (!basePath.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append(FILE_MAPPING.get(fileName)).append(File.separator);
        return sb;
    }

    /**
     * 文件夹不存在则创建
     *
     * @param sb
     */
    private void mkdirs(StringBuilder sb) {
        File file = new File(sb.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void loadBaseMapperTemplate() throws IOException {
        Configuration config = FreeMarkerConfig.getInstance();
//        tables.forEach(t -> doProcess(t , config));
        doProcessBaseMapper(config);
    }

    private void doProcessBaseMapper(Configuration config) {
        tables.forEach(t -> {
            for (File f : Objects.requireNonNull(FreeMarkerConfig.getResourceFile().listFiles())) {
                try {
                    if (f.getName().equals("MapperBase.ftl"))
                        createFile(t, config, f.getName());
                } catch (IOException | TemplateException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
