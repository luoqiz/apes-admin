<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
</#list>
import { Mutation, Action } from 'vuex';
import { StoreModuleType } from "@/utils/store";
import { ResponseData } from '@/utils/request';
import { ${tableInfo.className}DataType, TableDataType, TableListQueryParams } from './data.d';
import {
    queryList,
    removeData,
    createData,
    detailData,
    updateData,
    batchRemoveData,
} from './service';

export interface StateType {
    tableData: TableDataType;
    updateData: Partial<${tableInfo.className}DataType>;
}


export interface ModuleType extends StoreModuleType<StateType> {
    state: StateType;
    mutations: {
        setTableData: Mutation<StateType>;
        setUpdateData: Mutation<StateType>;
    };
    actions: {
        queryTableData: Action<StateType, StateType>;
        deleteTableData: Action<StateType, StateType>;
        batchDeleteTableData: Action<StateType, StateType>;
        createTableData: Action<StateType, StateType>;
        queryUpdateData: Action<StateType, StateType>;
        updateTableData: Action<StateType, StateType>;
    };
}

const initState: StateType = {
    tableData: {
        list: [],
        pagination: {
            total: 0,
            current: 1,
            pageSize: 10,
            showSizeChanger: true,
            showQuickJumper: true,
        },
    },
    updateData: {},
};

const StoreModel: ModuleType = {
    namespaced: true,
    name: '${tableInfo.className}ListSearchTable',
    state: {
        ...initState
    },
    mutations: {
        setTableData(state, payload) {
            state.tableData = payload;
        },
        setUpdateData(state, payload) {
            state.updateData = payload;
        },
    },
    actions: {
        async queryTableData({ commit }, payload: TableListQueryParams ) {
        try {
            const response: ResponseData = await queryList(payload);
            const { data } = response;
            commit('setTableData',{
                ...initState.tableData,
                list: data.data || [],
                pagination: {
                    ...initState.tableData.pagination,
                    current: payload.page,
                    pageSize: payload.size,
                    total: data.total || 0,
                },
            });
            return true;
        } catch (error) {
            return false;
        }
    },
    async deleteTableData({ commit }, payload: <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.tsColumnType}</#if></#list> ) {
        try {
            await removeData(payload);
            return true;
        } catch (error) {
            return false;
        }
    },
    async batchDeleteTableData({ commit }, payload: (number | string)[] ) {
        try {
            await batchRemoveData(payload);
            return true;
        } catch (error) {
            return false;
        }
    },
    async createTableData({ commit }, payload: Pick<${tableInfo.className}DataType, <#list tableInfo.colInfoList as field><#if field.nullValue==false ><#if field_index != 0> | </#if> "${field.javaColumnName}"</#if></#list>> ) {
        try {
            await createData(payload);
            return true;
        } catch (error) {
            return false;
        }
    },
    async queryUpdateData({ commit }, payload: <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.tsColumnType}</#if></#list> ) {
        try {
            const response: ResponseData = await detailData(payload);
            const { data } = response;
            commit('setUpdateData',{
                ...initState.updateData,
                ...data,
            });
            return true;
        } catch (error) {
            return false;
        }
    },
    async updateTableData({ commit }, payload: ${tableInfo.className}DataType ) {
            try {
                const { <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list> } = payload;
                await updateData(<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>, payload);
                return true;
            } catch (error) {
                return false;
            }
        },
    }
};

export default StoreModel;