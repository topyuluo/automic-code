package com.yuluo.auto.db;

import com.yuluo.auto.action.DBAction;
import com.yuluo.auto.source.FileResource;
import com.yuluo.auto.source.Resource;
import com.yuluo.auto.model.Table;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 执行数据库操作
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:35
 * @Version V1.0
 */
public class DBUtil {

    private static DBUtil db = new DBUtil();

    private Resource resource = null;

    private DBUtil() {
        resource = new Resource();
    }

    /**
     * 加载资源文件
     */
    public void init() {
        resource.load();
        process();
    }

    /**
     * 流程处理
     */
    public void process() {
        List<Table> tables = new DBAction(resource).getAllTables();
        FileResource fileResource = new FileResource(resource);
        tables.forEach(t -> fileResource.loadTemplate(t));
    }

    public static DBUtil getInstance() {
        return db;
    }

    public void load(HashMap<String, String> params) {
       resource.load(params);
       process();
    }

    public void load(String path) {
        resource.load(path);
        process();
    }
}
