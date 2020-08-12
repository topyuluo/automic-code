package com.yuluo.auto.service;

import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import com.yuluo.auto.constants.Constant;
import com.yuluo.auto.model.Column;
import com.yuluo.auto.model.Table;
import com.yuluo.auto.source.BaseResource;
import com.yuluo.auto.util.Assert;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.yuluo.auto.constants.Constant.*;
import static java.util.stream.Collectors.toList;

/**
 * @Description
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 23:51
 * @Version V1.0
 */
public class DbAction {
    private static Logger log = Logger.getLogger(DbAction.class);

    private final String MARK = "?";
    private DbFactory factory = null;
    private BaseResource resource;

    public DbAction(BaseResource resource) {
        this.factory = new DbFactory();
        this.resource = resource;
    }

    /**
     * 获取数据库中的所有表
     */
    public List<Table> getAllTables() throws MySQLTimeoutException {

        Connection conn = factory.getConnection(resource.getResource(0));
        Assert.notNull(conn, "Connection is not null !");

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
            log.error(e.getMessage());
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
    private String getDatabase(BaseResource resource) {
        String url = resource.getResource(0).getProperty(Constant.DB_URL);
        int start = url.lastIndexOf("/");
        int end = 0;
        if (url.contains(MARK)) {
            end = url.indexOf(MARK);
        } else {
            end = url.length();
        }
        return url.substring(start + 1, end);
    }


    private Table buildTable(ResultSet rs, final DatabaseMetaData metaData) throws SQLException {
        if (rs == null) {
            return null;
        }
        String tableName = rs.getString(TABLE_NAME);
        String comment = rs.getString(REMARKS);

        List<Column> columns = getAllColumns(tableName, metaData, resource.getResource(1));
        if (columns == null || columns.size() == 0) {
            throw new SQLException("没有查询到表中的字段信息!");
        }
        Table table = Table.newBuilder()
                .tableName(tableName)
                .comment(comment)
                .idType(getIdType(columns, tableName, metaData))
                .autoIncrement(getInCrement(columns))
                .basePackage(resource.getApplictionProperty(BASE_PACKAGE))
                .daoPackage(resource.getApplictionProperty(DAO_PACKAGE))
                .prefix(resource.getApplictionProperty(TABLE_PREFIX))
                .columns(columns)
                .lomback(resource.getApplictionProperty(LOMBACK))
                .build();
        log.info("load table - " + tableName);
        return table;
    }


    private String getInCrement(List<Column> columns) {
        List<String> ids = columns.stream()
                .filter(c -> "id".equals(c.getColumnName()))
                .map(Column::getAutoIncrement).collect(toList());
        return ids.isEmpty() ? "" : ids.get(0);
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
            String id = rskey.getString(COLUMN_NAME);
            keys = columns.stream()
                    .filter(c -> c.getColumnName().equals(id))
                    .map(Column::getColumnType)
                    .collect(toList());
        }
        return getJavaType(keys.size() == 0  ? "" : keys.get(0));
    }

    /**
     * 数据库类型转化为java 类型
     *
     * @param sqlType
     * @return
     */
    private String getJavaType(String sqlType) {
        if ("".equals(sqlType)) {
            return "";
        }
        return this.resource.getTypeMappingProperty(sqlType);
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
        ResultSet rs = metaData.getColumns(getDatabase(this.resource), null, tableName, null);
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
