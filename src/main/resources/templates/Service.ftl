package ${configInfo.packageService};


import org.springframework.stereotype.Service;

import java.util.List;
import ${configInfo.packageModel}.${table.upperCaseName};
/**
 * ${table.comment}服务类.
 */
@Service
public interface ${table.upperCaseName}Service {

    void insert(${table.upperCaseName} ${table.lowerCaseName});

    void update(${table.upperCaseName} ${table.lowerCaseName});

    void delete(${table.idType} id);

    ${table.upperCaseName} find(${table.idType} id);

    List<${table.upperCaseName}> findAll();


}

