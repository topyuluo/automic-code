package com.yuluo.auto.model;

import com.yuluo.auto.source.BaseResource;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 配置信息类
 * @Author 蚂蚁不是ant
 * @Date 2020/8/14 20:56
 * @Version V1.0
 */
public class ConfigInfo {

    private Logger log = Logger.getLogger(ConfigInfo.class);

    private boolean enableLomback;

    private String pathController;
    private String pathService;
    private String pathDao;
    private String pathMapper;
    private String pathModel;
    private String pathServiceImpl;

    private String packageController;
    private String packageService;
    private String packageServiceImpl;
    private String packageDao;
    private String packageModel;

    private List<String> tableNames;
    private boolean enableExcle;
    private String exclePath;


    public void resolveLomback(String value) {
        this.enableLomback = value != null;
        log.info("获取lomback配置 ：" + value);
    }

    public void resolveTableNames(String value) {
        if (value == null) {
            return;
        }
        String[] values = value.split(",");
        tableNames = new ArrayList<>(Arrays.asList(values));
    }

    public void resolvePath(BaseResource service) {
        this.pathController = getPathController(service);
        this.pathService = getPathService(service);
        this.pathDao = getPathDao(service);
        this.pathMapper = getPathMapper(service);
        this.pathModel = getPathModel(service);
        this.pathServiceImpl = getServiceImpl(service);
    }


    public void resolvePackage(BaseResource service) {
        this.packageController = getPackageController(service);
        this.packageDao = getPackageDao(service);
        this.packageService = getPackageService(service);
        this.packageModel = getPackageModel(service);
        this.packageServiceImpl = getPackageServiceImpl(service);
    }


    public String getAlias(String name) {
        if (name.startsWith("Mybatis")) {
            return getPathDao();
        }
        if (name.contains("Mapper")) {
            return getPathMapper();
        }
        throw new IllegalArgumentException("获取不到对应的参数！");
    }

    public void resolveExcle(BaseResource resource) {
        this.enableExcle = resource.getApplictionProperty("table.excle.export") != null;
        this.exclePath = resource.getApplictionProperty("table.excle.path") ;
    }

    private String getPackageModel(BaseResource service) {
        String value = service.getApplictionProperty("package.model");
        if (value == null) {
            value = service.getApplictionProperty("package.base");
            return value == null ? "orm.model" : value + ".orm.model";
        }
        return value;
    }

    private String getPackageService(BaseResource service) {
        String value = service.getApplictionProperty("package.service");
        if (value == null) {
            value = service.getApplictionProperty("package.base");
            return value == null ? "service" : value + ".service";
        }
        return value;
    }

    private String getPackageServiceImpl(BaseResource service) {
        String value = service.getApplictionProperty("package.service.impl");
        if (value == null) {
            return getPackageService(service) + ".impl";
        }
        return value;
    }

    private String getPackageMapper(BaseResource service) {
        String value = service.getApplictionProperty("package.mapper");
        if (value == null) {
            value = service.getApplictionProperty("package.base");
            return value == null ? "orm.mapper" : value + ".orm.mapper";
        }
        return value;
    }

    private String getPackageDao(BaseResource service) {
        String value = service.getApplictionProperty("package.dao");
        if (value == null) {
            value = service.getApplictionProperty("package.base");
            return value == null ? "orm.dao" : value + ".orm.dao";
        }
        return value;
    }

    private String getPackageController(BaseResource service) {
        String value = service.getApplictionProperty("package.controller");
        if (value == null) {
            value = service.getApplictionProperty("package.base");
            return value == null ? "controller" : value + ".controller";
        }
        return value;
    }

    private String getPathModel(BaseResource service) {
        String value = service.getApplictionProperty("path.model");
        if (value == null) {
            value = service.getApplictionProperty("path.base");
            if (value == null) {
                value = System.getProperty("user.dir");
            }
            return value + "/orm/model";
        }
        return value;
    }

    private String getPathMapper(BaseResource service) {
        String value = service.getApplictionProperty("path.mapper");
        if (value == null) {
            value = service.getApplictionProperty("path.base");
            if (value == null) {
                value = System.getProperty("user.dir");
            }
            return value + "/orm/mapper";
        }
        return value;
    }

    private String getPathDao(BaseResource service) {
        String value = service.getApplictionProperty("path.dao");
        if (value == null) {
            value = service.getApplictionProperty("path.base");
            if (value == null) {
                value = System.getProperty("user.dir");
            }
            return value + "/orm/dao";
        }
        return value;
    }

    private String getPathService(BaseResource service) {
        String value = service.getApplictionProperty("path.service");
        if (value == null) {
            value = service.getApplictionProperty("path.base");
            if (value == null) {
                value = System.getProperty("user.dir");
            }
            return value + File.separator + "service";
        }
        return value;
    }

    private String getServiceImpl(BaseResource service) {
        String value = service.getApplictionProperty("path.service.impl");
        if (value == null) {
            return getPathService(service) + File.separator + "impl";
        }
        return value;
    }

    private String getPathController(BaseResource service) {
        String value = service.getApplictionProperty("path.controller");
        if (value == null) {
            value = service.getApplictionProperty("path.base");
            if (value == null) {
                value = System.getProperty("user.dir");
            }
            return value + File.separator + "controller";
        }
        return value;
    }

    public boolean isEnableLomback() {
        return enableLomback;
    }

    public String getPathController() {
        return pathController;
    }

    public String getPathService() {
        return pathService;
    }

    public String getPathDao() {
        return pathDao;
    }

    public String getPathMapper() {
        return pathMapper;
    }

    public String getPathModel() {
        return pathModel;
    }

    public String getPackageController() {
        return packageController;
    }

    public String getPackageService() {
        return packageService;
    }

    public String getPackageDao() {
        return packageDao;
    }


    public String getPackageModel() {
        return packageModel;
    }

    public String getPathServiceImpl() {
        return pathServiceImpl;
    }

    public String getPackageServiceImpl() {
        return packageServiceImpl;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public boolean getEnableExcle() {
        return enableExcle;
    }

    public String getExclePath() {
        return exclePath;
    }
}
