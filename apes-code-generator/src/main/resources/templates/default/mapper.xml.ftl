<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("mapper.java.ftl")><#assign mapperFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
</#list>
<mapper namespace="${mapperFile.packagePath}.${tableInfo.className}${mapperFile.summary}">
    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <resultMap id="BaseResultMap" type="${entityFile.packagePath}.${tableInfo.className}${entityFile.summary}">
    <#list tableInfo.colInfoList as field>
        <#if field.primaryKey>
        <#-- 主键 -->
        <id column="${field.dbColumnName}" property="${field.javaColumnName}" />
        <#else>
        <#-- 普通字段 -->
        <result column="${field.dbColumnName}" property="${field.javaColumnName}" />
        </#if>
    </#list>
    </resultMap>
    <#------------  END 字段循环遍历  ---------->

    <sql id="columns">
        <#list tableInfo.colInfoList as field>
            <#if field_index==0>
                main.${field.dbColumnName}
            <#else>
                , main.${field.dbColumnName}
            </#if>
        </#list>
    </sql>

    <select id="selectListPage" resultMap="BaseResultMap">
        select
        <include refid="columns"/>
        from ${tableInfo.tableName} main
        join (select <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.dbColumnName}<#break /></#if></#list> from ${tableInfo.tableName} temp ${r'${'}ew.customSqlSegment${r'}'} limit ${r'${'}offset${r'}'},${r'${'}size${r'}'}) tmp
        on main.<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.dbColumnName}<#break /></#if></#list> = tmp.<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.dbColumnName}<#break /></#if></#list>
    </select>
</mapper>
