<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
</#list>
export interface ${tableInfo.className}DataType {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list tableInfo.colInfoList as field>
    ${field.javaColumnName}<#if field.nullValue>?</#if>: ${field.tsColumnType}; //${field.comment}
</#list>
<#------------  END 字段循环遍历  ---------->
}

export interface TableListQueryParams {
    page: number;
    size: number;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list tableInfo.colInfoList as field>
    <#if field.searchWay!?length lt 1>
        <#continue />
    </#if>
    ${field.javaColumnName}?: ${field.tsColumnType}; //${field.comment}
</#list>
<#------------  END 字段循环遍历  ---------->
}

export interface PaginationConfig {
    total: number;
    current: number;
    pageSize: number;
    showSizeChanger: boolean;
    showQuickJumper: boolean;
}


export interface TableDataType {
    list: TableListItem[];
    pagination: PaginationConfig;
}
