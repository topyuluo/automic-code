package com.yuluo.auto.service;

import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;
import java.util.Properties;

import static com.yuluo.auto.constants.Constant.DB_URL;

/**
 * @Description 数据库工厂类
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 22:00
 * @Version V1.0
 */
public class DbFactory {

    private static Logger log = Logger.getLogger(DbFactory.class);
    /**
     * 获取数据库连接
     *
     * @param properties
     * @return
     */
    private String mysql = "mysql";

    public Connection getConnection(Properties properties) throws MySQLTimeoutException {
        boolean result = getDbType(properties.getProperty(DB_URL));
        if (result) {
            return new MysqlDb(properties).getConnection();
        }
        throw new UnsupportedOperationException("currently only supports mysql database");
    }

    /**
     * 资源关闭
     *
     * @param conn
     */
    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据配置获取数据库类型
     *
     * @param property
     * @return
     */
    private boolean getDbType(String property) {
        if (null == property || "".equals(property)){
            throw new IllegalArgumentException("属性加载失败 ...." );
        }
        return property.toLowerCase().contains(mysql);
    }

    private static class MysqlDb {
        private Properties properties;

        public MysqlDb(Properties properties) {
            this.properties = properties;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("----> 注册驱动发生异常 <----");
            }
        }

        public Connection getConnection() throws MySQLTimeoutException {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
            } catch (SQLException e) {
                log.error(e);
            }
            return Optional.ofNullable(conn).orElseThrow(() -> new MySQLTimeoutException("获取数据库连接超时"));
        }
    }
}
