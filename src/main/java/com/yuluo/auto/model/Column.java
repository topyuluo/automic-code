package com.yuluo.auto.model;

import com.yuluo.auto.source.Resource;
import com.yuluo.auto.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description 存储字段信息
 * @Author 蚂蚁不是ant
 * @Date 2020/8/6 21:37
 * @Version V1.0
 */
public class Column {
    private String  columnName ;
    private String upperCaseName;
    private String lowerCaseName;
    private String columnType ;
    private String javaType ;
    private String comment ;
    private String autoIncrement;

    public Column(ResultSet rs , Properties resource) throws SQLException {
        this.columnName = rs.getString("COLUMN_NAME");
        this.columnType = rs.getString("TYPE_NAME");
        this.comment = rs.getString("REMARKS");
        this.autoIncrement = rs.getString("IS_AUTOINCREMENT");
        this.upperCaseName = StringUtils.getFirstUpperCaseName(this.columnName);
        this.lowerCaseName = StringUtils.getFirstLowerCaseName(this.upperCaseName);
        this.javaType = resource.getProperty(this.columnType);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", remarks='" + comment + '\'' +
                '}';
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
