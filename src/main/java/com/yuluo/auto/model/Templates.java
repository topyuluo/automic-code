package com.yuluo.auto.model;

import java.util.List;

/**
 * @Description 模板信息
 * @Author 蚂蚁不是ant
 * @Date 2020/8/14 21:09
 * @Version V1.0
 */
public class Templates {
    /** 表信息 */
    private List<Table>  table ;
    /** 配置信息 */
    private ConfigInfo info ;

//    public TemplateInfo(List<Table> table , ConfigInfo info){
//        this.table = table;
//        this.info = info;
//    }

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public ConfigInfo getInfo() {
        return info;
    }

    public void setInfo(ConfigInfo info) {
        this.info = info;
    }
}
