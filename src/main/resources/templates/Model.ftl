package ${basePackage}.model;

/**
 * ${comment}实体类.
 */
public class ${upperCaseName} {
    private static final long serialVersionUID = 1L;

    <#list columns as property>
    /**
    * ${property.comment}
    */
    private ${property.javaType} ${property.lowerCaseName};
        <#if property_has_next>

        </#if>
    </#list>

    <#list columns as property>
    /**
    * ${property.comment}的设置方法.
    */
    public void set${property.upperCaseName}(${property.javaType} ${property.lowerCaseName}) {
    this.${property.lowerCaseName} = ${property.lowerCaseName};
    }

    /**
    * ${property.comment}的取得方法.
    */
    public ${property.javaType} get${property.upperCaseName}() {
    return this.${property.lowerCaseName};
    }
    <#if property_has_next>

    </#if>
    </#list>
}
