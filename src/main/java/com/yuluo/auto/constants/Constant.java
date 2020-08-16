package com.yuluo.auto.constants;

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

    String TABLE_PREFIX = "table.cut";
    String LOMBACK = "lomback";

    String PATH = "path";
    String MODEL = "model";

    String UNDER_LINE = "_";
    String COMMA = ",";

    String[] TEMPLATES_FILE = {"Controller.ftl"
                                , "Dao.ftl"
                                , "Mapper.ftl"
                                , "MapperBase.ftl"
                                , "Model.ftl"
                                , "Service.ftl"
                                , "ServiceImpl.ftl"
                                , "MybatisMapper.ftl"};





}
