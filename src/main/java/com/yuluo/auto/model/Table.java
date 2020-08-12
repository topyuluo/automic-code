package com.yuluo.auto.model;

import com.mysql.cj.protocol.x.ReusableInputStream;
import com.yuluo.auto.util.StringUtils;

import java.util.List;

/**
 * @Description 表结构
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 22:39
 * @Version V1.0
 */
public class Table {

    /**
     * 数据库中表名
     */
    private String tableName;
    /**
     * 首字母大写 如果有下划线则进行处理
     */
    private String upperCaseName;
    /**
     * 首字母小写 如果有下划线进行处理
     */
    private String lowerCaseName;
    /**
     * 表名注释
     */
    private String comment;
    /**
     * 主键类型
     */
    private String idType;

    /**
     * id是否自动递增
     */
    private String autoIncrement;
    /**
     * 是否截取前缀  1 截取  0 不截取
     */
    private boolean isPrefix = false;
    /**
     * base package
     */
    private String basePackage;

    /**
     * dao的配置路径
     */
    private String daoPackage;

    private List<Column> columns;


    public Table(TableBuilder builder) {
        this.tableName = builder.tableName;
        this.isPrefix = builder.isPrefix;
        this.upperCaseName = StringUtils.getFirstUpperCaseName(prefix(this.tableName));
        this.lowerCaseName = StringUtils.getFirstLowerCaseName(this.upperCaseName);
        this.comment = builder.comment;
        this.idType = builder.idType;
        this.autoIncrement = builder.autoIncrement;

        this.autoIncrement = builder.autoIncrement;
        this.basePackage = builder.basePackage;
        this.daoPackage = builder.daoPackage;
        this.columns = builder.columns;
    }

    /**
     * 是否截取表前缀
     *
     * @param tableName
     * @return
     */
    private String prefix(String tableName) {
        if (this.isPrefix) {
            if (tableName.contains("_")) {
                return tableName.substring(tableName.indexOf("_") + 1);
            }
        }
        return tableName;
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

    public static TableBuilder newBuilder() {
        return new TableBuilder();
    }

    public static class TableBuilder {
        //数据库中表名
        private String tableName;
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

        public TableBuilder tableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public TableBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public TableBuilder idType(String idType) {
            this.idType = idType;
            return this;
        }

        public TableBuilder autoIncrement(String autoIncrement) {
            this.autoIncrement = autoIncrement;
            return this;
        }

        public TableBuilder prefix(String prefix) {
            isPrefix = "1".equals(prefix);
            return this;
        }

        public TableBuilder basePackage(String basePackage) {
            this.basePackage = basePackage;
            return this;
        }

        public TableBuilder daoPackage(String daoPackage) {
            this.daoPackage = daoPackage;
            return this;
        }

        public TableBuilder columns(List<Column> columns) {
            this.columns = columns;
            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }
}
