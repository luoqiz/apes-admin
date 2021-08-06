<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
</#list>
package ${file.packagePath};

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import org.springframework.beans.BeanUtils;

import java.util.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import ${entityFile.packagePath}.${tableInfo.className}${entityFile.summary};

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
public class ${tableInfo.className}${file.summary} extends ${superEntityClass} implements Serializable{
<#else>
public class ${tableInfo.className}${file.summary} implements Serializable {
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
    @ExcelProperty(value = "${field.comment}", index = ${field?index})
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


    public static ${tableInfo.className}${file.summary} fromEntity(${tableInfo.className}${entityFile.summary} entity) {
        if (entity == null) {
            return null;
        }
        ${tableInfo.className}${file.summary} vo = new ${tableInfo.className}${file.summary}();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<${tableInfo.className}${file.summary}> fromEntity(List<${tableInfo.className}${entityFile.summary}> entities) {
        LinkedList<${tableInfo.className}${file.summary}> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (${tableInfo.className}${entityFile.summary} entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }



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
