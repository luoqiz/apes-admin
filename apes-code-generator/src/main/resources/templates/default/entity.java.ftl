<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
</#list>
package ${file.packagePath};

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

<#if swagger3>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>

/**
 * <p>
 * ${tableInfo.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */
@TableName("${tableInfo.tableName}")
<#if swagger3>
@Schema(name = "${tableInfo.className}对象", description = "${tableInfo.comment!}")
</#if>
<#if superEntityClass??>
public class ${tableInfo.className}${file.summary} extends ${superEntityClass} {
<#else>
public class ${tableInfo.className}${file.summary} extends Model<${tableInfo.className}${file.summary}> {
</#if>

    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list tableInfo.colInfoList as field>
    <#if field.comment!?length gt 0>
        <#if swagger3>
    @Schema(description = "${field.comment}")
        <#else>
    /**
     * ${field.comment}
     */
        </#if>
    </#if>
    <#if field.primaryKey>
    <#-- 主键 -->
        <#if field.primaryKey>
    @TableId("${field.dbColumnName}")
        </#if>
    <#-- 普通字段 -->
    <#else>
  <#--  @TableField("${field.dbColumnName}") -->
    </#if>
    private ${field.javaColumnType} ${field.javaColumnName};

</#list>
<#------------  END 字段循环遍历  ---------->

    <#list tableInfo.colInfoList as field>
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

    @Override
    public String toString() {
        return "${tableInfo.className}{" +
    <#list tableInfo.colInfoList as field>
        <#if field_index==0>
               "${field.javaColumnName}=" + ${field.javaColumnName} +
        <#else>
               ", ${field.javaColumnName}=" + ${field.javaColumnName} +
        </#if>
    </#list>
               "}";
    }

}
