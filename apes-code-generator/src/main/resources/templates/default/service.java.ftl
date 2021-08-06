<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("queryCriteria.java.ftl")><#assign criteriaFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("vo.java.ftl")><#assign voFile=fileItem /></#if>
</#list>
package ${file.packagePath};

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import ${entityFile.packagePath}.${tableInfo.className}${entityFile.summary};
import ${criteriaFile.packagePath}.${tableInfo.className}${criteriaFile.summary};
import ${voFile.packagePath}.${tableInfo.className}${voFile.summary};

import java.util.*;

/**
 * <p>
 * ${tableInfo.comment!} 服务 接口
 * </p>
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */
public interface ${tableInfo.className}${file.summary} extends IService<${tableInfo.className}${entityFile.summary}> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List<${tableInfo.className}${voFile.summary}>>
     */
    PageResponse<List<${tableInfo.className}${voFile.summary}>> listSearch(${tableInfo.className}${criteriaFile.summary} criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<${tableInfo.className}${voFile.summary}>
     */
    List<${tableInfo.className}${voFile.summary}> listSearchAll(${tableInfo.className}${criteriaFile.summary} criteria);

    /**
     * 根据主键获取记录
     * @param id 主键
     * @return ${tableInfo.className}${voFile.summary}
     */
    ${tableInfo.className}${voFile.summary} getVoById(<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnType}</#if></#list> id);
}