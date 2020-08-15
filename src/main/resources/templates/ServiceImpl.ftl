package ${configInfo.packageServiceImpl};




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import ${configInfo.packageService}.${table.upperCaseName}Service;

import ${configInfo.packageDao}.${table.upperCaseName}Dao;
import ${configInfo.packageModel}.${table.upperCaseName};
/**
 * ${table.comment}服务类.
 */
@Service
public class ${table.upperCaseName}ServiceImpl implements ${table.upperCaseName}Service {

    @Autowired
    private ${table.upperCaseName}Dao ${table.lowerCaseName}Dao;

    @Override
    public void insert(${table.upperCaseName} ${table.lowerCaseName}) {
        ${table.lowerCaseName}Dao.insert(${table.lowerCaseName});
    }
    @Override
    public void update(${table.upperCaseName} ${table.lowerCaseName}) {
        ${table.lowerCaseName}Dao.update(${table.lowerCaseName});
    }
    @Override
    public void delete(${table.idType} id) {
        ${table.lowerCaseName}Dao.delete(id);
    }
    @Override
    public ${table.upperCaseName} find(${table.idType} id) {
        return ${table.lowerCaseName}Dao.find(id);
    }
    @Override
    public List<${table.upperCaseName}> findAll() {
        return ${table.lowerCaseName}Dao.findAll();
    }


}

