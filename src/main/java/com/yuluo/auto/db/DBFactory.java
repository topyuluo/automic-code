package com.yuluo.auto.db;

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
public class DBFactory {

    private static Logger log = Logger.getLogger(DBFactory.class);
    /**
     * 获取数据库连接
     *
     * @param properties
     * @return
     */
    private String MYSQL = "mysql";

    public Connection getConnection(Properties properties) {
        boolean result = getDBType(properties.getProperty(DB_URL));
        if (result) {
            try {
                return new MysqlDB(properties).getConnection();
            } catch (MySQLTimeoutException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 资源关闭
     * @param conn
     */
    public void close(Connection conn ){
        if (conn != null){
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
    private boolean getDBType(String property) {
        return property.toLowerCase().contains(MYSQL);
    }

    private static class MysqlDB {
        private Properties properties ;
        public MysqlDB(Properties properties) {
            this.properties = properties;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("----> 注册驱动发生异常 <----");
            }
        }

        public Connection getConnection() throws MySQLTimeoutException {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.ofNullable(conn).orElseThrow(()-> new MySQLTimeoutException("获取数据库连接超时"));
        }
    }
}
