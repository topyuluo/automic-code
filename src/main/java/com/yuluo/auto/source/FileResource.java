package com.yuluo.auto.source;

import com.yuluo.auto.freemark.FreeMarkerConfig;
import com.yuluo.auto.model.ConfigInfo;
import com.yuluo.auto.model.Table;
import com.yuluo.auto.model.TemplateInfo;
import com.yuluo.auto.model.Templates;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.yuluo.auto.constants.Constant.*;
import static java.util.stream.Collectors.toList;

/**
 * @Description 加载文件模板资源
 * @Author 蚂蚁不是ant
 * @Date 2020/8/6 22:28
 * @Version V1.0
 */
public class FileResource {

    private static Logger log = Logger.getLogger(FileResource.class);

    private Templates templates;

    public FileResource(Templates templates) {
        this.templates = templates;
    }

    /**
     * 加载模板信息
     */
    public void loadTemplate() throws IOException, TemplateException {
//        Configuration config = ;
//        tables.forEach(t -> doProcess(t , config));
        doProcess(FreeMarkerConfig.getInstance());
    }

    /**
     * 流程处理
     *
     * @param config
     */
    private void doProcess(Configuration config) throws IOException, TemplateException {
//        tables.forEach(t -> {
//            for (File f : Objects.requireNonNull(FreeMarkerConfig.getResourceFile().listFiles())) {
//                try {
//                    createFile(t, config, f.getName());
//                } catch (IOException | TemplateException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        String[] list = FreeMarkerConfig.getResourceFile().list();
        if (list == null) {
            throw new IllegalArgumentException("获取文件模板异常 ！");
        }
        List<String> collect = Arrays.asList(list);
        if (templates.getInfo().getTableNames() != null) {
            collect = collect.stream()
                        .filter(s -> s.contains("Base") || s.contains("Model"))
                        .collect(toList());
        }

        doCreateFile(config, collect);
    }

    private void doCreateFile(Configuration config, List<String> collect) {
        collect.forEach(t -> {
            try {
                createFile(templates, config, t);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        });
    }

    private String getName(String fileName){
        return fileName.substring(0, fileName.indexOf("."));
    }

    /**
     * 根据模板创建文件
     *
     * @param config
     * @throws IOException
     * @throws TemplateException
     */
    private void createFile(Templates templates, Configuration config, String name) throws IOException, TemplateException {
        Template template = config.getTemplate(name);
        name = getName(name);
        log.warn("load template - " + name);
        TemplateInfo info = null;
        ConfigInfo configInfo = templates.getInfo();
        for (Table table : templates.getTable()) {
            info = new TemplateInfo(table, configInfo);
            String fileName = getFilePath(table.getUpperCaseName(), name, configInfo);
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));) {
                template.process(info, writer);
            }
            log.info("create file - " + fileName);
        }

    }

    /**
     * 获取文件路径
     *
     * @param content
     * @param fileName
     * @return
     */
    private String getFilePath(String content, String fileName, ConfigInfo configInfo) {

        StringBuilder sb = new StringBuilder(getPath(fileName, configInfo));
        mkdirs(sb);

        String mapper = "Mapper";
        if (fileName.toLowerCase().contains(MODEL)) {
            sb.append(content);
        } else if (fileName.toLowerCase().startsWith("mybatis")) {
            sb.append(fileName);
        } else {
            sb.append(content).append(fileName);
        }
        if (fileName.startsWith(mapper)) {
            sb.append(".xml");
        } else {
            sb.append(".java");
        }
        return sb.toString();
    }

    private String getPath(String name, ConfigInfo configInfo) {



        Class<? extends ConfigInfo> clazz = configInfo.getClass();
        try {
//            PropertyDescriptor descriptor = new PropertyDescriptor("pathController", clazz);
//            return (String) descriptor.getReadMethod().invoke(configInfo);
            Method method = clazz.getDeclaredMethod("getPath" + name);
            return (String) method.invoke(configInfo);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.warn(e.getMessage() + "no such method exception");
        }
        return configInfo.getAlias(name);
//        String pathName = "path." + (fileName.substring(0, fileName.indexOf(".")).toLowerCase());
//        String path = resource.getApplictionProperty(pathName);
//        if (null == path || "".equals(path)) {
//            path = resource.getApplictionProperty(PATH);
//            if (null == path || "".equals(path)) {
//                path = System.getProperty("user.dir");
//            }
//        }
//        return path;
    }

//    /**
//     * 处理文件路径
//     *
//     * @param basePath
//     * @return
//     */
//    private StringBuilder handlePath(String basePath, String fileName) {
//        StringBuilder sb = new StringBuilder(basePath);
//        String backslash = "/";
//        if (basePath.contains(backslash)) {
//            basePath = basePath.replaceAll(backslash, File.separator);
//        }
//        if (!basePath.endsWith(File.separator)) {
//            sb.append(File.separator);
//        }
//        sb.append(FILE_MAPPING.get(fileName)).append(File.separator);
//        return sb;
//    }

    /**
     * 文件夹不存在则创建
     *
     * @param sb
     */
    private void mkdirs(StringBuilder sb) {
        sb.append(File.separator);
        File file = new File(sb.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

//    public void loadBaseMapperTemplate() throws IOException {
//        Configuration config = FreeMarkerConfig.getInstance();
////        tables.forEach(t -> doProcess(t , config));
//        doProcessBaseMapper(config);
//    }

//    private void doProcessBaseMapper(Configuration config) {
//        tables.forEach(t -> {
//            for (File f : Objects.requireNonNull(FreeMarkerConfig.getResourceFile().listFiles())) {
//                try {
//                    if (f.getName().equals("MapperBase.ftl"))
//                        createFile(t, config, f.getName());
//                } catch (IOException | TemplateException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
