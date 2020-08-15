package ${configInfo.packageDao};

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import ${configInfo.packageModel}.${table.upperCaseName};

/**
 * ${table.comment}数据访问类.
 */
@Repository
@Mapper
public interface ${table.upperCaseName}Dao extends MybatisMapper<${table.upperCaseName}>{

    List<${table.upperCaseName}> findAll();

}

