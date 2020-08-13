package ${packageService}


import org.springframework.stereotype.Service;

import java.util.List;
import ${packageModel}.${upperCaseName};
/**
 * ${comment}服务类.
 */
@Service
public interface ${upperCaseName}Service {

    void insert(${upperCaseName} ${lowerCaseName});

    void update(${upperCaseName} ${lowerCaseName});

    void delete(${idType} id);

    ${upperCaseName} find(${idType} id);

    List<${upperCaseName}> findAll();


}

