package ${configInfo.packageModel};

<#if configInfo.enableLomback == true>
import lombok.Data;
</#if>
/**
 * ${table.comment}实体类.
 */
<#if configInfo.enableLomback == true>
@Data
</#if>
public class ${table.upperCaseName} {
    private static final long serialVersionUID = 1L;

    <#list table.columns as property>
    /**
    * ${property.comment}
    */
    private ${property.javaType} ${property.lowerCaseName};
        <#if property_has_next>

        </#if>
    </#list>
    <#if configInfo.enableLomback == true>
    <#else>
    <#list table.columns as property>
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
    </#if>
}
