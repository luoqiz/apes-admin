package ${entityPackageName}.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${entityPackageName}.model.${entityName};
import ${entityPackageName}.model.${entityName}Condition;
import ${entityPackageName}.service.${entityName}Service;
import ${entityPackageName}.mapper.${entityName}Mapper;
@Service
public class ${entityName}ServiceImpl implements ${entityName}Service{

    @Autowired
    private ${entityName}Mapper ${entityName?uncap_first}Mapper;
    @Override 
    public int save${entityName}(${entityName} ${entityName?uncap_first}) throws Exception{
        return ${entityName?uncap_first}Mapper.save${entityName}(${entityName?uncap_first});
    }
    @Override
    public int save${entityName}Selective(${entityName} ${entityName?uncap_first}) throws Exception{
        return ${entityName?uncap_first}Mapper.save${entityName}Selective(${entityName?uncap_first});
    }
    @Override
    public int saveList${entityName}(List<${entityName}> ${entityName?uncap_first}List) throws Exception{
        return ${entityName?uncap_first}Mapper.saveList${entityName}(${entityName?uncap_first}List);
    }
    @Override
    public int count${entityName}Condition(${entityName}Condition ${entityName?uncap_first}Condition) throws Exception{
        return ${entityName?uncap_first}Mapper.count${entityName}Condition(${entityName?uncap_first}Condition);
    }
    @Override
    public List<${entityName}> list${entityName}Condition(${entityName}Condition ${entityName?uncap_first}Condition) throws Exception{
        return ${entityName?uncap_first}Mapper.list${entityName}Condition(${entityName?uncap_first}Condition);
    }
   
<#if javaTablePK??>
    @Override
    public int remove${entityName}ByPrimaryKey(String ${javaTablePK}) throws Exception{
        return ${entityName?uncap_first}Mapper.remove${entityName}ByPrimaryKey(${javaTablePK});
    }
    @Override
    public int removeList${entityName}ByPrimaryKey(String[] ${javaTablePK}Array) throws Exception{
        return ${entityName?uncap_first}Mapper.removeList${entityName}ByPrimaryKey(${javaTablePK}Array);
    }
    @Override
    public int update${entityName}ByPrimaryKey(${entityName} ${entityName?uncap_first}) throws Exception{
        return ${entityName?uncap_first}Mapper.update${entityName}ByPrimaryKey(${entityName?uncap_first});
    }
    @Override
    public int update${entityName}ByPrimaryKeySelective(${entityName} ${entityName?uncap_first}) throws Exception{
        return ${entityName?uncap_first}Mapper.update${entityName}ByPrimaryKeySelective(${entityName?uncap_first});
    }
    @Override
    public ${entityName} get${entityName}ByPrimaryKey(String ${javaTablePK}) throws Exception{
        return ${entityName?uncap_first}Mapper.get${entityName}ByPrimaryKey(${javaTablePK});
    }
 </#if>   
   public ${entityName}Mapper get${entityName}Mapper() {
        return ${entityName?uncap_first}Mapper;
    }

    public void set${entityName}Mapper(${entityName}Mapper ${entityName?uncap_first}Mapper) {
        this.${entityName?uncap_first}Mapper = ${entityName?uncap_first}Mapper;
    } 
}