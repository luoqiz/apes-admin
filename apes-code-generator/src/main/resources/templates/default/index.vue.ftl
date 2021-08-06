<template>
    <div class="indexlayout-main-conent">
        <a-card
            :bordered="true"
            style="margin-bottom: 15px"
            :bodyStyle="{paddingBottom: '0'}"
            v-show="searchOpen"
        >
            <a-form :labelCol="{ span: 6, offset: 0 }" :wrapper-col="{span:18}">
                <a-row :gutter="16" justify="start">
                    <#-- ----------  BEGIN 字段循环遍历  ---------->
                    <#list tableInfo.colInfoList as field>
                        <#if field.searchWay!?length lt 1><#continue /></#if>
                    <a-col :xs="24" :sm="24" :md="24" :lg="6" :xl="6">
                        <a-form-item  label="${field.comment}：" v-bind="searchValidateInfos.${field.javaColumnName}">
                        <#if field.tsColumnType=='string'>
                            <a-input placeholder="请输入" v-model:value="searchModelRef.${field.javaColumnName}" />
                        </#if>
                        <#if field.tsColumnType=='number'>
                            <a-input placeholder="请输入" v-model:value="searchModelRef.${field.javaColumnName}" />
                        </#if>
                        <#if field.tsColumnType=='boolean'>
                            <a-radio-group v-model:value="searchModelRef.${field.javaColumnName}">
                                <a-radio :style="radioStyle" :value="null">全部</a-radio>
                                <a-radio :style="radioStyle" :value="true">是</a-radio>
                                <a-radio :style="radioStyle" :value="false">否</a-radio>
                            </a-radio-group>
                        </#if>
                        <#if field.tsColumnType=='Date'>
                            <a-date-picker v-model:value="searchModelRef.${field.javaColumnName}" valueFormat='yyyy-MM-DD HH:mm:ss'/>
                        </#if>
                        </a-form-item>
                    </a-col>
                    </#list>
                    <#------------  END 字段循环遍历  ---------->
                </a-row>

                <a-row :gutter="16" justify="start">
                    <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="text-align-left" style="padding-bottom: 24px" >
                            <a-button type="primary" @click="searchFormSubmit">查询</a-button>
                            <a-button style="margin-left: 8px" @click="searchResetFields">重置</a-button>
                        </div>
                    </a-col>
                </a-row>
            </a-form>
        </a-card>


        <a-card :bordered="false">
            <template #title>
                <a-button type="primary" @click="() => setCreateFormVisible(true)">新增</a-button>
                <a-button type="danger" @click="() => batchDeleteTableData()" :disabled="selectIdlength < 1">删除</a-button>
            </template>
            <template #extra>
                <a-button  style="margin-left: 8px" @click="refresh">
                    刷新
                </a-button>
                <a-button  style="margin-left: 8px" @click="setSearchOpen">
                    <template v-if="searchOpen">
                        收起搜索 <UpOutlined />
                    </template>
                    <template v-else>
                        展开搜索 <DownOutlined />
                    </template>
                </a-button>
            </template>
            <a-table
                row-key="<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>"
                :columns="columns"
                :data-source="list"
                :loading="loading"
                :customRow="rowClick"
                :pagination="{
                    ...pagination,
                    onChange: async (page, pageSize) => {
                        await getList(page, pageSize);
                    },
                    onShowSizeChange: async (page, pageSize) => {
                        await getList(1, pageSize);
                    },
                }"
                bordered
                size="small"
                :row-selection="rowSelection"
                :scroll="{ x: 'calc(700px + 50%)', y: 600 }"
                :rowClassName="(record, index) => (index % 2 === 1 ? 'table-striped' : null)"
            >
                <template #c_title="{ text, record  }">
                    <a :href="record.href" target="_blank">{{text}}</a>
                </template>
                <template #action="{ record }">
                    <a-row type="flex" justify="center">
                        <a-col flex="1" offset=2>
                            <a-button type="primary" size="small" @click.stop="() => detailUpdateData(record.<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>)" :loading="detailUpdateLoading.includes(record.<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>)">编辑</a-button>
                        </a-col>
                        <a-col flex="1" offset=2>
                            <a-button type="danger" size="small" @click.stop="() => deleteTableData(record.<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>)" :loading="deleteLoading.includes(record.<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>)">删除</a-button>
                        </a-col>
                    </a-row>
                </template>
            </a-table>

            <create-form
                :visible="createFormVisible"
                :onCancel="() => setCreateFormVisible(false)"
                :onSubmitLoading="createSubmitLoading"
                :onSubmit="createSubmit"
            />

            <update-form
                v-if="updateFormVisible===true"
                :visible="updateFormVisible"
                :values="updateData"
                :onCancel="() => updataFormCancel()"
                :onSubmitLoading="updateSubmitLoading"
                :onSubmit="updateSubmit"
            />


        </a-card>
    </div>
</template>
<script lang="ts">
    import { computed, defineComponent, onMounted, reactive, ref } from "vue";
    import { useStore } from "vuex";
    import { message, Modal } from "ant-design-vue";
    import { UpOutlined,DownOutlined } from '@ant-design/icons-vue';
    import useForm, { Props, validateInfos } from "@ant-design-vue/use/lib/useForm";
    import CreateForm from './components/CreateForm.vue';
    import UpdateForm from './components/UpdateForm.vue';
    import { StateType as ListStateType } from "./store";
    import { PaginationConfig, ${tableInfo.className}DataType, TableListQueryParams } from './data.d';
    import moment from 'moment';

    interface ListSearchTablePageSetupData {
        columns: any;
        list: ${tableInfo.className}DataType[];
        pagination: PaginationConfig;
        loading: boolean;
        getList:  (current: number, pageSize: number) => Promise<void>;
        refresh: () => void;
        createFormVisible: boolean;
        setCreateFormVisible:  (val: boolean) => void;
        createSubmitLoading: boolean;
        createSubmit: (values: Omit<${tableInfo.className}DataType, '<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>'>, resetFields: (newValues?: Props | undefined) => void) => Promise<void>;
        detailUpdateLoading: number[];
        detailUpdateData: (<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}: ${field.tsColumnType}</#if></#list>) => Promise<void>;
        updateData: Partial<${tableInfo.className}DataType>;
        updateFormVisible: boolean;
        updataFormCancel:  () => void;
        updateSubmitLoading: boolean;
        updateSubmit:  (values: ${tableInfo.className}DataType, resetFields: (newValues?: Props | undefined) => void) => Promise<void>;
        deleteLoading: number[];
        deleteTableData:  (<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}: ${field.tsColumnType}</#if></#list>) => void;
        searchOpen: boolean;
        setSearchOpen: () => void;
        searchModelRef: TableListQueryParams;
        searchValidateInfos: validateInfos;
        searchResetFields: (newValues?: Props) => void;
        searchFormSubmit: () => Promise<void>;
        rowClick: (record: ${tableInfo.className}DataType, index: number) => {};
        rowSelection: {};
        selectIdlength: number;
        batchDeleteTableData: () => Promise<void>;
    }

    export default defineComponent({
        name: '${tableInfo.className}ListSearchTablePage',
        components: {
            CreateForm,
            UpdateForm,
            DownOutlined,
            UpOutlined
        },
        setup(): ListSearchTablePageSetupData {

            const rowClick = (record: ${tableInfo.className}DataType, index: number) => {
                return {
                    // 点击行
                    onClick:() => {
                        console.log(record);
                    },
                };
            }

            const selectIds = ref<(string | number)[]>([]);

            const rowSelection = {
                onChange: (selectedRowKeys: (string | number)[], selectedRows: ${tableInfo.className}DataType[]) => {
                    // console.log('onChange');
                    // console.log(`selectedRowKeys: ${r'${'}selectedRowKeys${r'}'}`, 'selectedRows: ', selectedRows);

                },
                onSelect: (record: ${tableInfo.className}DataType, selected: boolean, selectedRows: ${tableInfo.className}DataType[]) => {
                    // console.log('---------onSelect-----------');
                    // console.log(record, selected, selectedRows);
                    selectIds.value = selectedRows.map((item)=> {return item.<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>});
                },
                onSelectAll: (selected: boolean, selectedRows: ${tableInfo.className}DataType[], changeRows: ${tableInfo.className}DataType[]) => {
                    // console.log('-------***********--onSelectAll-**********----------');
                    // console.log(selected, selectedRows, changeRows);
                    selectIds.value = selectedRows.map((item)=> {return item.<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>});
                },
            };



            const selectIdlength = computed<number>(() => selectIds.value?.length || 0);

            const store = useStore<{ ${tableInfo.className}ListSearchTable: ListStateType}>();

            // 列表数据
            const list = computed<${tableInfo.className}DataType[]>(() => store.state.${tableInfo.className}ListSearchTable.tableData.list);

            // 列表分页
            const pagination = computed<PaginationConfig>(() => store.state.${tableInfo.className}ListSearchTable.tableData.pagination);

            // 列表字段
            const columns =[
                // {
                //     title: '序号',
                //     dataIndex: 'index',
                //     width: 80,
                //     customRender: ({text, index}: { text: any; index: number}) => (pagination.value.current - 1) * pagination.value.pageSize + index + 1,
                // },
                <#-- ----------  BEGIN 字段循环遍历  ---------->
                <#list tableInfo.colInfoList as field>
                <#if field.frontShow==false><#continue /></#if>
                {
                    title: '${field.comment}',
                    dataIndex: '${field.javaColumnName}',
                    width: 120,
                    <#if field_index == 0>fixed: 'left',</#if>
                <#if field.tsColumnType=='string'></#if>
                <#if field.tsColumnType=='number'></#if>
                <#if field.tsColumnType=='boolean'>
                    customRender: ({ text }: any)  =>{
                        return text ===true ? '是' : '否';
                    },
                </#if>
                <#if field.tsColumnType=='Date'>
                    customRender: ({ text }: any)  =>{
                        return !text ? "" : moment(text)?.format("yyyy-MM-DD HH:mm:ss");
                    },
                </#if>
                },
                </#list>
                <#------------  END 字段循环遍历  ---------->
                {
                    title: '操作',
                    key: 'action',
                    width: 150,
                    fixed: 'right',
                    slots: { customRender: 'action' },
                },
            ];

            // 搜索
            const searchOpen = ref<boolean>(false);
            const setSearchOpen = (): void => {
                searchOpen.value = !searchOpen.value;
            }
            // 搜索表单值
            const searchModelRef = reactive<TableListQueryParams>({
                page: 1,
                size: 10,
                <#-- ----------  BEGIN 字段循环遍历  ---------->
                <#list tableInfo.colInfoList as field>
                <#if field.searchWay!?length lt 1><#continue /></#if>
                <#if field.primaryKey==false>
                <#if field.tsColumnType=='string'>
                // ${field.javaColumnName}:'',
                </#if>
                <#if field.tsColumnType=='number'>
                <#--                ${field.javaColumnName}:0,-->
                </#if>
                <#if field.tsColumnType=='boolean'>
                // ${field.javaColumnName}:false,
                </#if>
                <#if field.tsColumnType=='Date'>
                // ${field.javaColumnName}: new Date(),
                </#if>
                </#if>
                </#list>
                <#------------  END 字段循环遍历  ---------->
            });
            // 搜索表单验证
            const searchRulesRef = reactive({
                <#-- ----------  BEGIN 字段循环遍历  ---------->
                <#list tableInfo.colInfoList as field>
                <#if field.searchWay!?length lt 1><#continue /></#if>
                ${field.javaColumnName}: [],
                </#list>
                <#------------  END 字段循环遍历  ---------->
            });
            // 获取表单内容
            const { resetFields, validate, validateInfos } = useForm(searchModelRef, searchRulesRef);

            // 获取数据
            const loading = ref<boolean>(true);
            const getList = async (current: number, pageSize: number): Promise<void> => {
                loading.value = true;
                try {
                    const filedsValue = await validate<Omit<${tableInfo.className}DataType, '<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>'>>();

                    await store.dispatch('${tableInfo.className}ListSearchTable/queryTableData', {
                        ...filedsValue,
                        ...{
                            page: current,
                            size: pageSize,
                        },
                    });
                    } catch (error) {
                        message.warning(error);
                    }
                loading.value = false;
            }

            const refresh = () => {
                getList(pagination.value.current, pagination.value.pageSize);
            }

            // 搜索
            const searchFormSubmit = async () => {
                await getList(1, pagination.value.pageSize);
            }

            // 新增弹框 - visible
            const createFormVisible = ref<boolean>(false);
            const setCreateFormVisible = (val: boolean) => {
                createFormVisible.value = val;
            };
            // 新增弹框 - 提交 loading
            const createSubmitLoading = ref<boolean>(false);
            // 新增弹框 - 提交
            const createSubmit = async (values: Omit<${tableInfo.className}DataType, '<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>'>, resetFields: (newValues?: Props | undefined) => void) => {
                createSubmitLoading.value = true;
                const res: boolean = await store.dispatch('${tableInfo.className}ListSearchTable/createTableData',values);
                if(res === true) {
                    resetFields();
                    setCreateFormVisible(false);
                    message.success('新增成功！');
                    getList(1, pagination.value.pageSize);
                }
                createSubmitLoading.value = false;
            }


            // 编辑弹框 - visible
            const updateFormVisible = ref<boolean>(false);
            const setUpdateFormVisible = (val: boolean) => {
                updateFormVisible.value = val;
            }
            const updataFormCancel = () => {
                setUpdateFormVisible(false);
                store.commit('${tableInfo.className}ListSearchTable/setUpdateData',{});
            }
            // 编辑弹框 - 提交 loading
            const updateSubmitLoading = ref<boolean>(false);
            // 编辑弹框 - 提交
            const updateSubmit = async (values: ${tableInfo.className}DataType, resetFields: (newValues?: Props | undefined) => void) => {
                updateSubmitLoading.value = true;
                console.log("values:",values);
                console.log("newValues:",values);
                const res: boolean = await store.dispatch('${tableInfo.className}ListSearchTable/updateTableData',values);
                if(res === true) {
                    updataFormCancel();
                    message.success('编辑成功！');
                    getList(pagination.value.current, pagination.value.pageSize);
                }
                updateSubmitLoading.value = false;
            }

            // 编辑弹框 data
            const updateData = computed<Partial<${tableInfo.className}DataType>>(() => store.state.${tableInfo.className}ListSearchTable.updateData);
            const detailUpdateLoading = ref<(string | number)[]>([]);
            const detailUpdateData = async (<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}: ${field.tsColumnType}</#if></#list>) => {
                detailUpdateLoading.value = [<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>];
                const res: boolean = await store.dispatch('${tableInfo.className}ListSearchTable/queryUpdateData', <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>);
                if(res===true) {
                    setUpdateFormVisible(true);
                }
                detailUpdateLoading.value = [];
            }

            // 删除 loading
            const deleteLoading = ref<(string | number)[]>([]);
            // 删除
            const deleteTableData = (<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}: ${field.tsColumnType}</#if></#list>) => {
                Modal.confirm({
                    title: '删除',
                    content: '确定删除吗？',
                    okText: '确认',
                    cancelText: '取消',
                    onOk: async () => {
                        deleteLoading.value = [<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>];
                        const res: boolean = await store.dispatch('${tableInfo.className}ListSearchTable/deleteTableData',<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>);
                        if (res === true) {
                            message.success('删除成功！');
                            getList(pagination.value.current, pagination.value.pageSize);
                        }
                        deleteLoading.value = [];
                    }
                });
            }

            const batchDeleteTableData = async ()=>{
                console.log(selectIds.value);
                Modal.confirm({
                    title: '删除',
                    content: '确定删除所选数据吗？',
                    okText: '确认',
                    cancelText: '取消',
                    onOk: async () => {
                        deleteLoading.value = selectIds.value;
                        const res: boolean = await store.dispatch('${tableInfo.className}ListSearchTable/batchDeleteTableData',selectIds.value);
                        if (res === true) {
                            message.success('删除成功！');
                            getList(pagination.value.current, pagination.value.pageSize);
                        }
                        deleteLoading.value = [];
                    }
                });
            }

            onMounted(()=> {
                getList(1, pagination.value.pageSize);
            })

            return {
                columns,
                list: list as unknown as ${tableInfo.className}DataType[],
                pagination: pagination as unknown as PaginationConfig,
                loading: loading as unknown as boolean,
                getList,
                refresh,
                createFormVisible: createFormVisible as unknown as boolean,
                setCreateFormVisible,
                createSubmitLoading: createSubmitLoading as unknown as boolean,
                createSubmit,
                detailUpdateLoading: detailUpdateLoading as unknown as number[],
                detailUpdateData,
                updateData: updateData as Partial<${tableInfo.className}DataType>,
                updateFormVisible: updateFormVisible as unknown as boolean,
                updataFormCancel,
                updateSubmitLoading: updateSubmitLoading as unknown as boolean,
                updateSubmit,
                deleteLoading: deleteLoading as unknown as number[],
                deleteTableData,
                searchOpen: searchOpen as unknown as boolean,
                setSearchOpen,
                searchModelRef,
                searchValidateInfos: validateInfos,
                searchResetFields: resetFields,
                searchFormSubmit,
                rowClick,
                rowSelection,
                batchDeleteTableData,
                selectIdlength: selectIdlength as unknown as number,
            }

        }

    })
</script>