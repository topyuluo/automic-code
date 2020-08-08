package ${basePackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import ${basePackage}.service.${upperCaseName}Service;
import <#if daoPackage??>${daoPackage}<#else>${basePackage}</#if>.dao.${upperCaseName}Dao;
import <#if daoPackage??>${daoPackage}<#else>${basePackage}</#if>.model.${upperCaseName};
/**
 * ${comment}服务类.
 */
@Service
public class ${upperCaseName}ServiceImpl implements ${upperCaseName}Service {

    @Autowired
    private ${upperCaseName}Dao ${lowerCaseName}Dao;

    @Override
    public void insert(${upperCaseName} ${lowerCaseName}) {
        ${lowerCaseName}Dao.insert(${lowerCaseName});
    }
    @Override
    public void update(${upperCaseName} ${lowerCaseName}) {
        ${lowerCaseName}Dao.update(${lowerCaseName});
    }
    @Override
    public void delete(${idType} id) {
        ${lowerCaseName}Dao.delete(id);
    }
    @Override
    public ${upperCaseName} find(${idType} id) {
        return ${lowerCaseName}Dao.find(id);
    }
    @Override
    public List<${upperCaseName}> findAll() {
        return ${lowerCaseName}Dao.findAll();
    }


}

