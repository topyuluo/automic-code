package <#if daoPackage??>${daoPackage}<#else>${basePackage}</#if>.orm.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import <#if daoPackage??>${daoPackage}<#else>${basePackage}</#if>.orm.model.${upperCaseName};

/**
 * ${comment}数据访问类.
 */
@Repository
@Mapper
public interface ${upperCaseName}Dao {

    List<${upperCaseName}> findAll();

    int insert(${upperCaseName} t);

    int update(${upperCaseName} t);

    int delete(Object t);

    ${upperCaseName} find(Object id);
}

