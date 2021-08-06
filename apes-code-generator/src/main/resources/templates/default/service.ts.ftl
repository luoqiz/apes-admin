<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
</#list>
import request from '@/utils/request';
import {${tableInfo.className}DataType, TableListQueryParams} from './data'

export async function queryList(params?: TableListQueryParams): Promise<any> {
    return request({
        url: '${tableInfo.className?uncap_first}/search',
        method: 'get',
        params,
    });
}

export async function createData(params: Omit<${tableInfo.className}DataType, '<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>'>): Promise<any> {
    return request({
        url: '${tableInfo.className?uncap_first}',
        method: 'POST',
        data: params,
    });
}

export async function updateData(<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>: <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.tsColumnType}</#if></#list>, params: Omit<${tableInfo.className}DataType, '<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>'>): Promise<any> {
    return request({
        url: `${tableInfo.className?uncap_first}`,
        method: 'PUT',
        data: params,
    });
}

export async function removeData(<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>: <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.tsColumnType}</#if></#list>): Promise<any> {
    return request({
        url: `${tableInfo.className?uncap_first}/${r'${'}<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>${r'}'}`,
        method: 'delete',
    });
}

export async function batchRemoveData(<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>: (number | string)[]): Promise<any> {
    return request({
        url: `${tableInfo.className?uncap_first}`,
        method: 'delete',
        data: <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>,
    });
}

export async function detailData(<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>: <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.tsColumnType}</#if></#list>): Promise<any> {
    return request({url: `${tableInfo.className?uncap_first}/${r'${'}<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>${r'}'}`});
}