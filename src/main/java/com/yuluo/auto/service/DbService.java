package com.yuluo.auto.service;

import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import com.yuluo.auto.model.Templates;
import com.yuluo.auto.source.FileResource;
import com.yuluo.auto.model.Table;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 执行数据库操作
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:35
 * @Version V1.0
 */
public class DbService {

    private Logger log = Logger.getLogger(DbService.class);

    private AutoService service = null;

    private Templates templates;

    public DbService(AutoService service , Templates templates) {
        this.service = service;
        this.templates = templates;
    }

    /**
     * 流程处理
     */
    public List<Table> process(List<String> tableNames) throws MySQLTimeoutException, IOException {
        DbAction dbAction = new DbAction(service);
        List<Table> tables = null;
        if (tableNames == null || tableNames.isEmpty()) {
            tables =dbAction.getAllTables();
        }else {
           tables = dbAction.getTables(tableNames);
        }
        dbAction.close();
        templates.setTable(tables);
        return tables;


//        String tableMapperBase = service.getApplictionProperty("table.mapper.base");
//        List<Table> tables = null;
//        if (null != tableMapperBase) {
//            String[] tableNames = tableMapperBase.split(",");
//            tables = dbAction.getTables(tableNames);
//            new FileResource(service, tables).loadBaseMapperTemplate();
//        } else {
//            tables = dbAction.getAllTables();
//
//        }

    }
}
