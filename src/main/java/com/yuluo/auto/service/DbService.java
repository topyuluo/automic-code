package com.yuluo.auto.service;

import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import com.yuluo.auto.source.FileResource;
import com.yuluo.auto.model.Table;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @Description 执行数据库操作
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:35
 * @Version V1.0
 */
public class DbService {

    private Logger log = Logger.getLogger(DbService.class);

    private AutoService service = null;

    public DbService(AutoService service) {
        this.service = service;
    }

    /**
     * 流程处理
     */
    public void process() throws MySQLTimeoutException, IOException {
        List<Table> tables = new DbAction(service).getAllTables();
        new FileResource(service, tables).loadTemplate();
    }

}
