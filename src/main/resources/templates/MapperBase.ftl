<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${configInfo.packageDao}.${table.upperCaseName}Dao">
    <resultMap id="${table.lowerCaseName}Map" type="${configInfo.packageModel}.${table.upperCaseName}">
    <#list table.columns as property>
        <#if property.lowerCaseName == "id">
        <id property="id" column="id"/>
        <#else>
        <result property="${property.lowerCaseName}" column="${property.columnName}" />
        </#if>
    </#list>
    </resultMap>

    <insert id="insert">
        <#if table.autoIncrement == "YES">
        <#assign f = 0 />
        insert into ${table.tableName}(<#list table.columns as property><#if property.lowerCaseName != "id" && property.lowerCaseName != "updateTime"><#if f == 1>, </#if>${property.lowerCaseName}<#assign f = 1 /></#if></#list>)
        <#assign f = 0 />
        values(<#list table.columns as property><#if property.lowerCaseName != "id" && property.lowerCaseName != "updateTime"><#if f == 1>, </#if>${'#'}${'{'}${property.lowerCaseName}${'}'}<#assign f = 1 /></#if></#list>)
        <selectKey keyProperty="id" resultType="${table.idType}" order="AFTER" >
            select last_insert_id() as value
        </selectKey>
        </#if>
    </insert>

    <update id="update">
        update ${table.tableName}
        <trim prefix="SET" suffixOverrides=",">
        <#list table.columns as property>
            <#if property.lowerCaseName != "id" && property.lowerCaseName != "updateTime">
                <if test="${property.lowerCaseName} != null">${property.lowerCaseName} = ${'#'}${'{'}${property.lowerCaseName}${'}'},</if>
            </#if>
        </#list>
        </trim>
        where id = ${'#'}${'{'}id${'}'}
    </update>

    <delete id="delete">
        delete from ${table.tableName} where ${'id'} = ${'#'}${'{'}${table.columns[0].columnName}${'}'}
    </delete>

    <select id="find" resultMap="${table.lowerCaseName}Map">
        select <#list table.columns as property>${property.columnName}<#if property_has_next>, </#if></#list> from ${table.tableName} where id = ${'#'}${'{'}id${'}'}
    </select>
</mapper>