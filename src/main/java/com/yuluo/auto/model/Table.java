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
    private String underLine = "_";
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


    private List<Column> columns;
    private boolean isPrefix = false;


    public Table(TableBuilder builder) {
        this.tableName = builder.tableName;
        this.isPrefix = builder.isPrefix;
        this.upperCaseName = StringUtils.getFirstUpperCaseName(prefix(this.tableName));
        this.lowerCaseName = StringUtils.getFirstLowerCaseName(this.upperCaseName);
        this.comment = builder.comment;
        this.idType = builder.idType;
        this.autoIncrement = builder.autoIncrement;

        this.autoIncrement = builder.autoIncrement;
//        this.basePackage = builder.basePackage;
//        this.daoPackage = builder.daoPackage;
        this.columns = builder.columns;
//        this.lomback = builder.lomback;
//        this.packageController = builder.packageController;
//        this.packageService = builder.packageService;
//        this.packageDao = builder.packageDao ;
//        this.packageMapper = builder.packageMapper;
//        this.packageModel = builder.packageModel;
        processPackagePath();
    }

    private String prefix(String tableName) {
        if (!isPrefix) {
            return tableName;
        } else if (tableName.contains(underLine)) {
            return tableName.substring(tableName.indexOf(underLine) + 1);
        }
        return tableName;
    }

    private void processPackagePath() {
//        if (packageController == null) {
//            this.packageController = basePackage + ".controller";
//        }
//        if (packageDao == null){
//            this.packageDao = basePackage + ".orm.dao";
//        }
//        if (packageMapper == null){
//            this.packageMapper = basePackage + ".orm.mapper";
//        }
//        if (packageModel == null){
//            this.packageModel = basePackage + ".orm.model";
//        }
//        if (packageService == null){
//            this.packageService = basePackage + ".service";
//        }
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

//    public boolean isPrefix() {
//        return isPrefix;
//    }
//
//    public void setPrefix(boolean prefix) {
//        isPrefix = prefix;
//    }
//
//    public String getBasePackage() {
//        return basePackage;
//    }
//
//    public void setBasePackage(String basePackage) {
//        this.basePackage = basePackage;
//    }
//
//    public String getDaoPackage() {
//        return daoPackage;
//    }
//
//    public void setDaoPackage(String daoPackage) {
//        this.daoPackage = daoPackage;
//    }

    public static TableBuilder newBuilder() {
        return new TableBuilder();
    }

//    public String getLomback() {
//        return lomback;
//    }
//
//    public void setLomback(String lomback) {
//        this.lomback = lomback;
//    }
//
//    public String getPackageController() {
//        return packageController;
//    }
//
//    public String getPackageService() {
//        return packageService;
//    }
//
//    public String getPackageDao() {
//        return packageDao;
//    }
//
//    public String getPackageMapper() {
//        return packageMapper;
//    }
//
//    public String getPackageModel() {
//        return packageModel;
//    }

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
            isPrefix = prefix != null;
            return this;
        }



        public TableBuilder columns(List<Column> columns) {
            this.columns = columns;
            return this;
        }

//        public TableBuilder lomback(String lomback) {
//            this.lomback = lomback;
//            return this;
//        }

        public Table build() {
            return new Table(this);
        }

//        public TableBuilder packageController(String packageController) {
//            this.packageController = packageController;
//            return this;
//        }
//        public TableBuilder packageService(String packageService) {
//            this.packageService = packageService;
//            return this;
//        }
//        public TableBuilder packageDao(String packageDao) {
//            this.packageDao = packageDao;
//            return this;
//        }
//        public TableBuilder packageMapper(String packageMapper) {
//            this.packageMapper = packageMapper;
//            return this;
//        }
//        public TableBuilder packageModel(String packageModel) {
//            this.packageModel = packageModel;
//            return this;
//        }


    }
}
