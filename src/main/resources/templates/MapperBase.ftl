<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="<#if daoPackage??>${daoPackage}<#else>${basePackage}</#if>.orm.dao.${upperCaseName}Dao">
    <resultMap id="${lowerCaseName}Map" type="<#if daoPackage??>${daoPackage}<#else>${basePackage}</#if>.orm.model.${upperCaseName}">
    <#list columns as property>
        <#if property.lowerCaseName == "id">
        <id property="id" column="id"/>
        <#else>
        <result property="${property.lowerCaseName}" column="${property.columnName}" />
        </#if>
    </#list>
    </resultMap>

    <insert id="insert">
        <#if autoIncrement == "YES">
        <#assign f = 0 />
        insert into ${tableName}(<#list columns as property><#if property.lowerCaseName != "id" && property.lowerCaseName != "updateTime"><#if f == 1>, </#if>${property.lowerCaseName}<#assign f = 1 /></#if></#list>)
        <#assign f = 0 />
        values(<#list columns as property><#if property.lowerCaseName != "id" && property.lowerCaseName != "updateTime"><#if f == 1>, </#if>${'#'}${'{'}${property.lowerCaseName}${'}'}<#assign f = 1 /></#if></#list>)
        <selectKey keyProperty="id" resultType="${idType}" order="AFTER" >
            select last_insert_id() as value
        </selectKey>

        <#else >
        <#assign f = 0 />
        insert into ${clazz.table.name}(<#list clazz.propertyList as property><#if property.lowerCaseName != "updateTime"><#if f == 1>, </#if>${property.lowerCaseName}<#assign f = 1 /></#if></#list>)
        <#assign f = 0 />
        values(<#list clazz.propertyList as property><#if property.lowerCaseName != "updateTime"><#if f == 1>, </#if>${'#'}${'{'}${property.lowerCaseName}${'}'}<#assign f = 1 /></#if></#list>)
        </#if>
    </insert>

    <update id="update">
        update ${tableName}
        <trim prefix="SET" suffixOverrides=",">
        <#list columns as property>
            <#if property.lowerCaseName != "id" && property.lowerCaseName != "updateTime">
                <if test="${property.lowerCaseName} != null">${property.lowerCaseName} = ${'#'}${'{'}${property.lowerCaseName}${'}'},</if>
            </#if>
        </#list>
        </trim>
        where id = ${'#'}${'{'}id${'}'}
    </update>

    <delete id="delete">
        delete from ${tableName} where ${'id'} = ${'#'}${'{'}${columns[0].columnName}${'}'}
    </delete>

    <select id="find" resultMap="${lowerCaseName}Map">
        select <#list columns as property>${property.columnName}<#if property_has_next>, </#if></#list> from ${tableName} where id = ${'#'}${'{'}id${'}'}
    </select>
</mapper>