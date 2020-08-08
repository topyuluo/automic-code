package com.yuluo.auto.model;

import com.yuluo.auto.util.StringUtils;

import java.util.List;

/**
 * @Description 表结构
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 22:39
 * @Version V1.0
 */
public class Table {

    //数据库中表名
    private String tableName;
    //首字母大写 如果有下划线则进行处理
    private String upperCaseName;
    //首字母小写 如果有下划线进行处理
    private String lowerCaseName;
    //表名注释
    private String comment;
    //主键类型
    private String idType;

    //id是否自动递增
    private String autoIncrement;
    /*是否截取前缀*/
    private boolean isPrefix = false;
    /*base package*/
    private String basePackage;

    /*dao的配置路径*/
    private String daoPackage;

    private List<Column> columns;

    public Table(String tableName, String comment) {
        this.tableName = tableName.toLowerCase();
        this.upperCaseName = StringUtils.getFirstUpperCaseName(this.tableName);
        this.lowerCaseName = StringUtils.getFirstLowerCaseName(this.upperCaseName);
        this.comment = comment;
    }


    public String getAutoIncrement() {
        return autoIncrement;
    }
    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUpperCaseName() {
        return upperCaseName;
    }

    public void setUpperCaseName(String upperCaseName) {
        this.upperCaseName = upperCaseName;
    }

    public String getLowerCaseName() {
        return lowerCaseName;
    }

    public void setLowerCaseName(String lowerCaseName) {
        this.lowerCaseName = lowerCaseName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public boolean isPrefix() {
        return isPrefix;
    }

    public void setPrefix(boolean prefix) {
        isPrefix = prefix;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }
}
