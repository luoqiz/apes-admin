package ${entityPackageName}.service;

import java.util.List;

import ${entityPackageName}.model.${entityName};
import ${entityPackageName}.model.${entityName}Condition;
public interface ${entityName}Service{

    /**
    * @Title: save${entityName}   
    * @Description: 添加一条记录(操作所有字段)
    * @param ${entityName}
    * @return int
    * @throws Exception
     */
    int save${entityName}(${entityName} ${entityName?uncap_first}) throws Exception;

    /**
    * @Title: save${entityName}Selective      
    * @Description: 添加一条记录(只操作不为空的字段)
    * @param ${entityName}
    * @return int 
    * @throws Exception
     */
    int save${entityName}Selective(${entityName} ${entityName?uncap_first}) throws Exception;
    
    /**
    * @Title: saveList${entityName}      
    * @Description: 批量添加记录(只操作不为空的字段)
    * @param ${entityName}
    * @return int 
    * @throws Exception
     */
    int saveList${entityName}(List<${entityName}> ${entityName?uncap_first}List) throws Exception;
    
    /**
    * @Title: count${entityName}Condition      
    * @Description: 根据条件获取记录总数
    * @param ${entityName}Condition
    * @return int
    * @throws Exception 
     */
    int count${entityName}Condition(${entityName}Condition ${entityName?uncap_first}Condition) throws Exception;
    
    /**
    * @Title: list${entityName}Condition      
    * @Description: 根据条件获取记录
    * @param ${entityName}Condition
    * @return List<${entityName}> 
    * @throws Exception 
     */
    List<${entityName}> list${entityName}Condition(${entityName}Condition ${entityName?uncap_first}Condition) throws Exception;
  
<#if javaTablePK??>

    /**
    * @Title: remove${entityName}ByPrimaryKey     
    * @Description: 根据主键删除一条记录 
    * @param ${javaTablePK}
    * @return int 
    * @throws Exception
     */
    int remove${entityName}ByPrimaryKey(String ${javaTablePK}) throws Exception;
    
    /**
    * @Title: removeList${entityName}ByPrimaryKey     
    * @Description: 根据主键批量删除记录 
    * @param ${javaTablePK}Array
    * @return int 
    * @throws Exception
     */
    int removeList${entityName}ByPrimaryKey(String[] ${javaTablePK}Array) throws Exception;
    
    /**
    * @Title: update${entityName}ByPrimaryKey     
    * @Description: 根据主键更新一条记录（所有字段更新）
    * @param ${entityName}
    * @return int
    * @throws Exception
     */
    int update${entityName}ByPrimaryKey(${entityName} ${entityName?uncap_first}) throws Exception;
    
    /**
    * @Title: update${entityName}ByPrimaryKeySelective     
    * @Description: 根据主键更新一条记录（不为空 的字段更新）
    * @param ${entityName}
    * @return int
    * @throws Exception
     */
    int update${entityName}ByPrimaryKeySelective(${entityName} ${entityName?uncap_first}) throws Exception;
    
    
    /**
    * @Title: get${entityName}ByPrimaryKey  
    * @Description: 根据主键获取单条记录
    * @param ${entityName}Condition
    * @return ${entityName}
    * @throws Exception
     */
    ${entityName} get${entityName}ByPrimaryKey(String ${javaTablePK}) throws Exception;
    </#if>   
}