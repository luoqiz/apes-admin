package ${entityPackageName}.model;
<#if importUtil==true>
import java.util.*;
</#if>
<#if importSql==true>
import java.sql.*;
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ${entityName} {
	<#list arrayList as row>
	@ApiModelProperty(value="${row["comment"]}",position = ${row_index?if_exists+1})
	private ${row["javaColumnType"]} ${row["javaColumnName"]};
	
	</#list>
	
	<#list arrayList as row>
	public void set${row["javaColumnName"]?cap_first}(${row["javaColumnType"]} ${row["javaColumnName"]}) {
        <#if row["javaColumnType"]=='String'>
         this.${row["javaColumnName"]} = ${row["javaColumnName"]} == null ? null : ${row["javaColumnName"]}.trim();
        <#else>
        this.${row["javaColumnName"]} = ${row["javaColumnName"]};
        </#if>
    }
    
	public ${row["javaColumnType"]} get${row["javaColumnName"]?cap_first}(){
		return ${row["javaColumnName"]};
	}
	</#list>
}