package com.yuluo.auto.model;

/**
 * @Description
 * @Author 蚂蚁不是ant
 * @Date 2020/8/15 0:16
 * @Version V1.0
 */
public class TemplateInfo {
    private Table table ;
    private ConfigInfo configInfo;

    public TemplateInfo(Table table, ConfigInfo configInfo) {
        this.table = table;
        this.configInfo = configInfo;
    }

    public Table getTable() {
        return table;
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }
}
