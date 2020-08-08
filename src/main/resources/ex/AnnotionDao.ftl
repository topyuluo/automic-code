package cn.insightcredit.cloud.app.loan.orm.dao;

import cn.insightcredit.cloud.app.loan.orm.model.${clazz.name};
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${clazz.comment}数据访问类.
 */
@Repository
@Mapper
public interface ${clazz.name}Dao {

    /** ============================================================================================================= */
    //  auto generator
    /** ============================================================================================================= */
    @Results(id = "${clazz.lowerCaseName}Map", value = {
        <#list clazz.propertyList as property>
            <#if property.name == "${clazz.primaryProperty.name}">
            @Result(property = "${property.name}", column = "${property.column.name}", id = true),
            <#else>
            @Result(property="${property.name}", column="${property.column.name}")<#if property_index < (clazz.propertyList?size)>, </#if>
            </#if>
        </#list>
    })
    @Select("select * from ${clazz.table.name} where ${clazz.table.primaryKey.name} = ${'#'}${'{'}${clazz.primaryProperty.name}${'}'}")
    ${clazz.name} find(Integer id);

    <#if clazz.table.primaryKey.name == 'id'>
    <#assign f = 0 />
    @Insert("insert into ${clazz.table.name}(<#list clazz.propertyList as property><#if property.name != "id" && property.name != "updatedAt"><#if f == 1>, </#if>${property.column.name}<#assign f = 1 /></#if></#list>) " +
    <#assign f = 0 />
    "values (<#list clazz.propertyList as property><#if property.name != "id" && property.name != "updatedAt"><#if f == 1>, </#if>${'#'}${'{'}${property.name}${'}'}<#assign f = 1 /></#if></#list>")
    @SelectKey(keyProperty="id", resultType=Integer.class, statement = "select last_insert_id() as value", before = false )
    </#if>
    <#if clazz.table.primaryKey.name != 'id'>
    <#assign f = 0 />
    @Insert("insert into ${clazz.table.name}(<#list clazz.propertyList as property><#if property.name != "updatedAt"><#if f == 1>, </#if>${property.column.name}<#assign f = 1 /></#if></#list>) " +
    <#assign f = 0 />
    "values (<#list clazz.propertyList as property><#if property.name != "updatedAt"><#if f == 1>, </#if>${'#'}${'{'}${property.name}${'}'}<#assign f = 1 /></#if></#list>)")
    </#if>
    int insert(${clazz.name} ${clazz.lowerCaseName});

    <#assign f = 0 />
    @Update("update ${clazz.table.name} set <#list clazz.propertyList as property><#if property.name != 'createdAt' && property.name != 'updatedAt'><#if (f > 0)>, </#if>${property.column.name} = ${'#'}${'{'}${property.name}${'}'}<#assign f = f + 1 /></#if></#list> where ${clazz.table.primaryKey.name} = ${'#'}${'{'}${clazz.primaryProperty.name}${'}'}")
    int update(${clazz.name} ${clazz.lowerCaseName});

    @Delete("delete from ${clazz.table.name} where ${clazz.table.primaryKey.name} = ${'#'}${'{'}${clazz.primaryProperty.name}${'}'}")
    int delete(Integer id);

    /** ============================================================================================================= */
    //  code
    /** ============================================================================================================= */
    @ResultMap("${clazz.lowerCaseName}Map")
    @Select("select * from ${clazz.table.name} order by id desc")
    List<${clazz.name}> paginate();
}

