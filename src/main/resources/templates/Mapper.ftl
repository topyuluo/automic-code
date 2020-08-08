<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="<#if daoPackage??>${daoPackage}<#else>${basePackage}</#if>.orm.dao.${upperCaseName}Dao">
    <select id="findAll" resultMap="${lowerCaseName}Map">
        select * from ${tableName}
    </select>
</mapper>