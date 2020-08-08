package com.yuluo.auto.action;

import com.yuluo.auto.db.DBFactory;
import com.yuluo.auto.model.Column;
import com.yuluo.auto.model.Table;
import com.yuluo.auto.source.Resource;
import com.yuluo.auto.util.Assert;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.util.stream.Collectors.toList;

/**
 * @Description
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 23:51
 * @Version V1.0
 */
public class DBAction {
    private DBFactory factory = null;
    private Resource resource;

    public DBAction(Resource resource) {
        this.factory = new DBFactory();
        this.resource = resource;
    }

    /**
     * 获取数据库中的所有表
     */
    public List<Table> getAllTables() {

        Connection conn = factory.getConnection(resource.getResource(0));
        DatabaseMetaData metaData = null;
        List<Table> tables = new ArrayList<>();
        try {
            metaData = conn.getMetaData();
            ResultSet rs = metaData.getTables(getDatabase(resource), null, null, new String[]{"TABLE"});
            Table table = null;
            while (rs.next()) {
                table = buildTable(rs, metaData);
                tables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("---> 获取MetaData异常 <----");
        } finally {
            factory.close(conn);
        }
        return tables;
    }

    /**
     * 截取数据库名字
     *
     * @param resource
     * @return
     */
    private String getDatabase(Resource resource) {
        String url = resource.getResource(0).getProperty("db.url");
        int start = url.lastIndexOf("/");
        int end = 0;
        if (url.contains("?")) {
            end = url.indexOf("?");
        } else {
            end = url.length();
        }
        return url.substring(start + 1, end);
    }


    private Table buildTable(ResultSet rs, final DatabaseMetaData metaData) throws SQLException {
        if (rs == null) {
            return null;
        }
        String tableName = rs.getString("TABLE_NAME");
        String comment = rs.getString("Remarks");
        Table table = new Table(tableName, comment);
        List<Column> columns = getAllColumns(tableName, metaData, resource.getResource(1));
        table.setColumns(columns);
        table.setIdType(getIdType(columns, tableName, metaData));
        table.setAutoIncrement(getInCrement(columns));
        table.setBasePackage(resource.getResource(0).getProperty("base.package"));
        table.setDaoPackage(resource.getResource(0).getProperty("dao.package"));
        System.out.println("-----------> 加载表 ：" + tableName + " <-----------------");
        return table;
    }

    private String getInCrement(List<Column> columns) {
        List<String> ids = columns.stream()
                .filter(c -> c.getColumnName().equals("id"))
                .map(Column::getAutoIncrement).collect(toList());
        return ids.get(0);
    }

    /**
     * 获取主键的类型
     *
     * @param columns
     * @param tableName
     * @param metaData
     * @return
     */
    private String getIdType(List<Column> columns, String tableName, DatabaseMetaData metaData) throws SQLException {
        ResultSet rskey = metaData.getPrimaryKeys(null, null, tableName);

        List<String> keys = null;
        while (rskey.next()) {
            String id = rskey.getString("COLUMN_NAME");
            keys = columns.stream()
                    .filter(c -> c.getColumnName().equals(id))
                    .map(Column::getColumnType)
                    .collect(toList());
        }
        return getJavaType(keys.get(0));
    }

    /**
     * 数据库类型转化为java 类型
     *
     * @param sqlType
     * @return
     */
    private String getJavaType(String sqlType) {
        Assert.notEmpty(sqlType, "sql type is not null ");
        return this.resource.getResource(1).getProperty(sqlType);
    }

    /**
     * 获取表的所有字段信息
     *
     * @param tableName
     * @param metaData
     * @param resource
     * @return
     */
    private List<Column> getAllColumns(String tableName, DatabaseMetaData metaData, Properties resource) throws SQLException {
        ResultSet rs = metaData.getColumns(null, null, tableName, null);
        List<Column> columns = null;
        Column column = null;
        while (rs.next()) {
            if (columns == null) {
                columns = new ArrayList<>();
            }
            column = new Column(rs, resource);
            columns.add(column);
        }
        return columns;
    }
}
