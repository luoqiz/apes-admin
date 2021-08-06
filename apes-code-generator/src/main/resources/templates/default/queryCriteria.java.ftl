<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
</#list>
package ${file.packagePath};

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;
import java.time.LocalDateTime;

/**
 * <p>
 * ${tableInfo.comment!} 查询条件构建类
 * </p>
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */
public class ${tableInfo.className}${file.summary} extends BasePageCriteria {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list tableInfo.colInfoList as field>
    <#if field.searchWay!?length lt 1>
        <#continue />
    </#if>
    <#if field.comment!?length gt 0>
        <#if swagger3>
    @Schema(description = "${field.comment}")
        <#else>
    /**
    * ${field.comment}
    */
        </#if>
    </#if>
    private ${field.javaColumnType} ${field.javaColumnName};

</#list>
<#------------  END 字段循环遍历  ---------->

<#list tableInfo.colInfoList as field>
    <#if field.searchWay!?length lt 1>
        <#continue />
    </#if>

    <#if field.javaColumnType == "boolean">
        <#assign getprefix="is"/>
    <#else>
        <#assign getprefix="get"/>
    </#if>
    public ${field.javaColumnType} ${getprefix}${field.javaColumnName?cap_first}() {
        return ${field.javaColumnName};
    }

    <#if chainModel>
    public ${tableInfo.className}${file.summary} set${field.javaColumnName?cap_first}(${field.javaColumnName} ${field.javaColumnName}) {
    <#else>
    public void set${field.javaColumnName?cap_first}(${field.javaColumnType} ${field.javaColumnName}) {
    </#if>
        this.${field.javaColumnName} = ${field.javaColumnName};
    <#if chainModel>
        return this;
    </#if>
    }

</#list>


}