<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("queryCriteria.java.ftl")><#assign criteriaFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("mapper.java.ftl")><#assign mapperFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("service.java.ftl")><#assign iserviceFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("vo.java.ftl")><#assign voFile=fileItem /></#if>
</#list>
package ${file.packagePath};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import ${entityFile.packagePath}.${tableInfo.className}${entityFile.summary};
import ${criteriaFile.packagePath}.${tableInfo.className}${criteriaFile.summary};
import ${voFile.packagePath}.${tableInfo.className}${voFile.summary};
import ${mapperFile.packagePath}.${tableInfo.className}${mapperFile.summary};
import ${iserviceFile.packagePath}.${tableInfo.className}${iserviceFile.summary};

import java.util.*;

/**
 * <p>
 * ${tableInfo.comment!} 服务 实现类
 * </p>
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */
@Service
public class ${tableInfo.className}${file.summary} extends ServiceImpl<${tableInfo.className}${mapperFile.summary}, ${tableInfo.className}${entityFile.summary}> implements ${tableInfo.className}${iserviceFile.summary}  {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List<${tableInfo.className}${voFile.summary}>>
     */
    @Override
    public PageResponse<List<${tableInfo.className}${voFile.summary}>> listSearch(${tableInfo.className}${criteriaFile.summary} criteria) {
        LambdaQueryWrapper<${tableInfo.className}${entityFile.summary}> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        List<${tableInfo.className}${entityFile.summary}> listEntity = baseMapper.selectListPage(queryWrapper, (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize());
        return PageResponse.build(${tableInfo.className}${voFile.summary}.fromEntity(listEntity), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件
     * @return List<${tableInfo.className}${voFile.summary}>
     */
    @Override
    public List<${tableInfo.className}${voFile.summary}> listSearchAll(${tableInfo.className}${criteriaFile.summary} criteria){
        LambdaQueryWrapper<${tableInfo.className}${entityFile.summary}> queryWrapper = buildQuery(criteria);
        return ${tableInfo.className}${voFile.summary}.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return ${tableInfo.className}${voFile.summary}
     */
    @Override
    public ${tableInfo.className}${voFile.summary} getVoById(<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnType}</#if></#list> id) {
        ${tableInfo.className}${entityFile.summary} resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return ${tableInfo.className}${voFile.summary}.fromEntity(resource);
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<${tableInfo.className}${entityFile.summary}>
     */
    private LambdaQueryWrapper<${tableInfo.className}${entityFile.summary}> buildQuery(${tableInfo.className}${criteriaFile.summary} criteria) {

        LambdaQueryWrapper<${tableInfo.className}${entityFile.summary}> queryWrapper = new LambdaQueryWrapper<>();
        <#list tableInfo.colInfoList as field>
            <#if field.searchWay!?length lt 1>
                <#continue />
            </#if>
            <#if field.javaColumnType == "boolean">
                <#assign getprefix="is"/>
            <#else>
                <#assign getprefix="get"/>
            </#if>
            <#if field.searchWay == "=">
        Optional.ofNullable(criteria.${getprefix}${field.javaColumnName?cap_first}()).ifPresent(value -> queryWrapper.eq(${tableInfo.className}${entityFile.summary}::${getprefix}${field.javaColumnName?cap_first}, value));
            </#if>
            <#if field.searchWay == "<">
        Optional.ofNullable(criteria.${getprefix}${field.javaColumnName?cap_first}()).ifPresent(value -> queryWrapper.lt(${tableInfo.className}${entityFile.summary}::${getprefix}${field.javaColumnName?cap_first}, value));
            </#if>
            <#if field.searchWay == ">">
        Optional.ofNullable(criteria.${getprefix}${field.javaColumnName?cap_first}()).ifPresent(value -> queryWrapper.gt(${tableInfo.className}${entityFile.summary}::${getprefix}${field.javaColumnName?cap_first}, value));
            </#if>
            <#if field.searchWay == "like">
        Optional.ofNullable(criteria.${getprefix}${field.javaColumnName?cap_first}()).ifPresent(value -> queryWrapper.like(${tableInfo.className}${entityFile.summary}::${getprefix}${field.javaColumnName?cap_first}, value));
            </#if>
        </#list>
        return queryWrapper;
    }
}