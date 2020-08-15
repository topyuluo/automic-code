<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${configInfo.packageDao}.${table.upperCaseName}Dao">
    <select id="findAll" resultMap="${table.lowerCaseName}Map">
        select * from ${table.tableName}
    </select>
</mapper>