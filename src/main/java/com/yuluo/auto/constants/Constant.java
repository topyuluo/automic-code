package com.yuluo.auto.constants;

import java.util.HashMap;

/**
 * @Description
 * @Author 蚂蚁不是ant
 * @Date 2020/8/8 22:23
 * @Version V1.0
 */
public interface Constant {

    String DB_URL = "db.url";

    String TABLE_NAME = "TABLE_NAME";
    String REMARKS = "Remarks";
    String COLUMN_NAME = "COLUMN_NAME";
    String TYPE_NAME = "TYPE_NAME";
    String UPPER_REMARKS = "REMARKS";
    String IS_AUTOINCREMENT = "IS_AUTOINCREMENT";

    String BASE_PACKAGE = "base.package";
    String DAO_PACKAGE = "dao.package";

    String TABLE_PREFIX = "table.prefix";

    String PATH = "path";
    String MAPPER = "model";

    String UNDER_LINE = "_";

    String[] TEMPLATES_FILE = {"Controller.ftl", "Dao.ftl", "Mapper.ftl", "MapperBase.ftl", "Model.ftl", "Service.ftl", "ServiceImpl.ftl"};

    HashMap<String, String> FILE_MAPPING = new HashMap<String, String>() {{
        put("Controller.ftl", "controller");
        put("Dao.ftl", "orm\\dao");
        put("Mapper.ftl", "orm\\mapper");
        put("MapperBase.ftl", "orm\\mapper");
        put("Model.ftl", "orm\\model");
        put("Service.ftl", "service");
        put("ServiceImpl.ftl", "service\\impl");
    }};



}
