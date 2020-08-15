package com.yuluo.auto.service;

import com.yuluo.auto.model.ConfigInfo;
import com.yuluo.auto.model.Templates;
import com.yuluo.auto.source.BaseResource;

/**
 * @Description 配置信息处理类
 * @Author 蚂蚁不是ant
 * @Date 2020/8/14 21:13
 * @Version V1.0
 */
public class ConfigService {

    private ConfigInfo configInfo = null;
    private BaseResource service = null;
    private Templates templates;

    public ConfigService(AutoService service , Templates templates) {
        this.service = service;
        this.configInfo = new ConfigInfo();
        templates.setInfo(this.configInfo);
    }

    public ConfigInfo process() {
        configInfo.resolveTableNames(service.getApplictionProperty("table.name"));
        configInfo.resolveLomback(service.getApplictionProperty("lomback.enable"));
        configInfo.resolvePath(service);
        configInfo.resolvePackage(service);
        configInfo.resolveExcle(service.getApplictionProperty("table.export.excle"));
        return configInfo;
    }
}
