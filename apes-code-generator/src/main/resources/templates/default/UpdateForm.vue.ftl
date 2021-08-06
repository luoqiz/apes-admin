<template>
    <a-modal
            :destroy-on-close="true"
            :mask-closable="false"
            title="编辑"
            :visible="visible"
            :onCancel="onCancel"
            width=50%
    >
        <template #footer>
            <a-button key="back" @click="() => onCancel()">取消</a-button>
            <a-button key="submit" type="primary" :loading="onSubmitLoading" @click="onFinish">提交</a-button>
        </template>

        <a-form :labelCol="{ span: 4 }" :wrapper-col="{span:20}">

        <#list tableInfo.colInfoList as field>
            <#if field.frontShow==false>                <#continue />                </#if>
            <a-form-item label="${field.comment}" v-bind="validateInfos.${field.javaColumnName}">
                <#if field.tsColumnType=='string'>
                <a-input v-model:value="modelRef.${field.javaColumnName}" placeholder="请输入${field.comment}" />
                </#if>
                <#if field.tsColumnType=='number'>
                <a-input-number v-model:value="modelRef.${field.javaColumnName}"/>
                </#if>
                <#if field.tsColumnType=='boolean'>
                <a-radio-group v-model:value="modelRef.${field.javaColumnName}">
                    <a-radio :style="radioStyle" :value="true">是</a-radio>
                    <a-radio :style="radioStyle" :value="false">否</a-radio>
                </a-radio-group>
                </#if>
                <#if field.tsColumnType=='Date'>
                <a-input v-model:value="modelRef.${field.javaColumnName}" placeholder="请输入${field.comment}" />
<#--                <a-date-picker v-model:value="modelRef.${field.javaColumnName}" valueFormat='yyyy-MM-DD HH:mm:ss'/>-->
                </#if>
            </a-form-item>
        </#list>

        </a-form>


    </a-modal>
</template>
<script lang="ts">
import { defineComponent, PropType, reactive } from "vue";
import { useI18n } from "vue-i18n";
import { message } from "ant-design-vue";
import { useForm } from "@ant-design-vue/use";
// import CKEditor from "@/components/CKEditor/index.vue";
import { ${tableInfo.className}DataType } from "../data.d";
import { Props, validateInfos } from "@ant-design-vue/use/lib/useForm";

interface UpdateFormSetupData {
    modelRef: Omit<${tableInfo.className}DataType, '<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>'>;
    validateInfos: validateInfos;
    onFinish: () => Promise<void>;
}

export default defineComponent({
    name: 'UpdateForm',
    props: {
        visible: {
            type: Boolean,
            required: true
        },
        values: {
            type: Object as PropType<Partial<${tableInfo.className}DataType>>,
            required: true
        },
        onCancel: {
            type: Function,
            required: true
        },
        onSubmitLoading: {
            type: Boolean,
            required: true
        },
        onSubmit: {
            type: Function as PropType<(values: Omit<${tableInfo.className}DataType, '<#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnName}</#if></#list>'>, resetFields: (newValues?: Props | undefined) => void) => void>,
            required: true
        }
    },
    components: {
        // CKEditor
    },
    setup(props): UpdateFormSetupData {

        const { t } = useI18n();

        // 表单值
        const modelRef = reactive<${tableInfo.className}DataType>({
            <#-- ----------  BEGIN 字段循环遍历  ---------->
            <#list tableInfo.colInfoList as field>
            <#if field.frontShow==false> <#continue /> </#if>
            <#if field.tsColumnType=='string'>
            ${field.javaColumnName}: props.values.${field.javaColumnName} || '',
            </#if>
            <#if field.tsColumnType=='number'>
            ${field.javaColumnName}: props.values.${field.javaColumnName} || 0,
            </#if>
            <#if field.tsColumnType=='boolean'>
            ${field.javaColumnName}: props.values.${field.javaColumnName} || false,
            </#if>
            <#if field.tsColumnType=='Date'>
            ${field.javaColumnName}: props.values.${field.javaColumnName},
            </#if>
            </#list>
            <#------------  END 字段循环遍历  ---------->
        });
        // 表单验证
        const rulesRef = reactive({
            <#-- ----------  BEGIN 字段循环遍历  ---------->
            <#list tableInfo.colInfoList as field>
            <#if field.primaryKey==true>
            ${field.javaColumnName}: [],
                <#continue />
            </#if>
            <#if field.frontShow==false>  <#continue />   </#if>
            ${field.javaColumnName}: [],
            </#list>
            <#------------  END 字段循环遍历  ---------->
        });
        // 获取表单内容
        const { resetFields, validate, validateInfos } = useForm(modelRef, rulesRef);
        // 提交
        const onFinish = async () => {
            try {
                const fieldsValue = await validate<${tableInfo.className}DataType>();
                console.log("fieldsValue",fieldsValue)
                props.onSubmit(fieldsValue, resetFields);
            } catch (error) {
                // console.log('error', error);
                message.warning(t('app.global.form.validatefields.catch'));
            }
        };

        return {
            modelRef,
            validateInfos,
            onFinish
        }

    }
})
</script>