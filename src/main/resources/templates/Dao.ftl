package ${packageDao};

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import ${packageDao}.${upperCaseName};

/**
 * ${comment}数据访问类.
 */
@Repository
@Mapper
public interface ${upperCaseName}Dao extends MabatisMapper<${upperCaseName}>{

    List<${upperCaseName}> findAll();

}

