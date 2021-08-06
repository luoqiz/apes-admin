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
public class ${entityName}Condition extends ${entityName}{
	
	//第几页
    @ApiModelProperty(value = "第几页")
    private int pageNumber;
    
    // 每页条目数量
    @ApiModelProperty(value = "每页条目数量")
    private int pageSize;
    
    // 在mysql中limit后因无法计算，需要将初始条数记录完成计算
    @ApiModelProperty(value = "在mysql中limit后因无法计算，需要将初始条数记录完成计算")
    private int startRowNumber = (pageNumber - 1) * pageSize;
    
    // 排序字段名称
    @ApiModelProperty(value = "排序字段名称")
    private String sortName;
    
    // 排序方式
    @ApiModelProperty(value = "排序方式 升序asc，降序desc，默认降序")
    private String sortOrder = "DESC";
    
	<#list arrayList as row>
		<#if row["searchWay"]?? && row["searchWay"] != "=">
		<#if row["searchWay"]==">">
			private ${row["javaColumnType"]} ${row["javaColumnName"]}LT;
		</#if>
		<#if row["searchWay"]=="<">
			private ${row["javaColumnType"]} ${row["javaColumnName"]}GT;
		</#if>
		<#if row["searchWay"]=="<>">
			private ${row["javaColumnType"]} ${row["javaColumnName"]}LT;
			private ${row["javaColumnType"]} ${row["javaColumnName"]}GT;
		</#if>
		<#if row["searchWay"]=="like">
		private ${row["javaColumnType"]} ${row["javaColumnName"]}Like;
		</#if>
		</#if>
	</#list>
	
	
	<#list arrayList as row>
		<#if row["searchWay"]!?? && row["searchWay"]!="=">
		<#if row["searchWay"]==">">
			public void set${row["javaColumnName"]?cap_first}LT(${row["javaColumnType"]} ${row["javaColumnName"]}LT) {
		        <#if row["javaColumnType"]=='String'>
		         this.${row["javaColumnName"]}LT = ${row["javaColumnName"]}LT == null ? null : ${row["javaColumnName"]}LT.trim();
		        <#else>
		        this.${row["javaColumnName"]}LT = ${row["javaColumnName"]}LT;
		        </#if>
	    	}
    
			public ${row["javaColumnType"]} get${row["javaColumnName"]?cap_first}LT(){
				return ${row["javaColumnName"]}LT;
			}
	
		</#if>
		<#if row["searchWay"]=="<">
			public void set${row["javaColumnName"]?cap_first}GT(${row["javaColumnType"]} ${row["javaColumnName"]}LT) {
		        <#if row["javaColumnType"]=='String'>
		         this.${row["javaColumnName"]}GT = ${row["javaColumnName"]}GT == null ? null : ${row["javaColumnName"]}GT.trim();
		        <#else>
		        this.${row["javaColumnName"]}GT = ${row["javaColumnName"]}GT;
		        </#if>
	    	}
    
			public ${row["javaColumnType"]} get${row["javaColumnName"]?cap_first}GT(){
				return ${row["javaColumnName"]}GT;
			}
		</#if>
		<#if row["searchWay"]=="<>">
			public void set${row["javaColumnName"]?cap_first}LT(${row["javaColumnType"]} ${row["javaColumnName"]}LT) {
		        <#if row["javaColumnType"]=='String'>
		         this.${row["javaColumnName"]}LT = ${row["javaColumnName"]}LT == null ? null : ${row["javaColumnName"]}LT.trim();
		        <#else>
		        this.${row["javaColumnName"]}LT = ${row["javaColumnName"]}LT;
		        </#if>
	    	}
    
			public ${row["javaColumnType"]} get${row["javaColumnName"]?cap_first}LT(){
				return ${row["javaColumnName"]}LT;
			}
			
			public void set${row["javaColumnName"]?cap_first}GT(${row["javaColumnType"]} ${row["javaColumnName"]}GT) {
		        <#if row["javaColumnType"]=='String'>
		         this.${row["javaColumnName"]}GT = ${row["javaColumnName"]}GT == null ? null : ${row["javaColumnName"]}GT.trim();
		        <#else>
		        this.${row["javaColumnName"]}GT = ${row["javaColumnName"]}GT;
		        </#if>
	    	}
    
			public ${row["javaColumnType"]} get${row["javaColumnName"]?cap_first}GT(){
				return ${row["javaColumnName"]}GT;
			}
		</#if>
		<#if row["searchWay"]=="like">
			public void set${row["javaColumnName"]?cap_first}Like(${row["javaColumnType"]} ${row["javaColumnName"]}Like) {
		        <#if row["javaColumnType"]=='String'>
		         this.${row["javaColumnName"]}Like = ${row["javaColumnName"]}Like == null ? null : ${row["javaColumnName"]}Like.trim();
		        <#else>
		        this.${row["javaColumnName"]}Like = ${row["javaColumnName"]}Like;
		        </#if>
	    	}
    
			public ${row["javaColumnType"]} get${row["javaColumnName"]?cap_first}Like(){
				return ${row["javaColumnName"]}Like;
			}
		</#if>
		</#if>
	</#list>
	
	 public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getStartRowNumber() {
        return startRowNumber;
    }

    public void setStartRowNumber(int startRowNumber) {
        this.startRowNumber = startRowNumber;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
}