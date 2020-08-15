package com.yuluo.auto.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.yuluo.auto.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.yuluo.auto.constants.Constant.*;

/**
 * @Description 存储字段信息
 * @Author 蚂蚁不是ant
 * @Date 2020/8/6 21:37
 * @Version V1.0
 */
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(15)
public class Column {

    @ExcelProperty(value = "字段名")
    private String columnName;

    @ExcelIgnore
    private String upperCaseName;

    @ExcelIgnore
    private String lowerCaseName;

    @ExcelProperty(value = "类型")
    private String columnType;

    @ExcelIgnore
    private String javaType;
    @ExcelProperty("长度")
    private Integer length;

    @ExcelProperty("小数点")
    private Integer radixPoint;

    @ExcelProperty("null")
    private Integer nullAble;


    @ExcelProperty(value = "是否自增")
    private String autoIncrement;

    @ExcelProperty(value = "默认值")
    private String columnDef;

    @ExcelProperty("主键")
    private String key;

    @ExcelProperty(value = "注释")
    private String comment;

    public Column(ResultSet rs, Properties resource) throws SQLException {
        this.columnName = rs.getString(COLUMN_NAME);
        this.columnType = rs.getString(TYPE_NAME);
        this.comment = rs.getString(UPPER_REMARKS);
        this.autoIncrement = rs.getString(IS_AUTOINCREMENT);
        this.upperCaseName = StringUtils.getFirstUpperCaseName(this.columnName);
        this.lowerCaseName = StringUtils.getFirstLowerCaseName(this.upperCaseName);
        this.javaType = resource.getProperty(this.columnType);
        this.length = rs.getInt("COLUMN_SIZE");
        this.radixPoint = rs.getInt("DECIMAL_DIGITS");
        this.nullAble = rs.getInt("NULLABLE");
        this.columnDef = rs.getString("COLUMN_DEF");  //默认值
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getRadixPoint() {
        return radixPoint;
    }

    public void setRadixPoint(Integer radixPoint) {
        this.radixPoint = radixPoint;
    }

    public Integer getNullAble() {
        return nullAble;
    }

    public void setNullAble(Integer nullAble) {
        this.nullAble = nullAble;
    }

    public String getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }
}
