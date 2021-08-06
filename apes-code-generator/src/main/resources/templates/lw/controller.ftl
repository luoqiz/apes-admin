package ${entityPackageName}.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itic.goods.utils.MsgJson;

import ${entityPackageName}.model.${entityName};
import ${entityPackageName}.model.${entityName}Condition;
import ${entityPackageName}.service.${entityName}Service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/${entityName?uncap_first}")
public class ${entityName}Controller{

    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;
    
    @ApiOperation(value = "全数据添加", notes = "对所有字段处理添加记录")
    @PostMapping(value = "/save")
    @ResponseBody
    public MsgJson save${entityName}(@RequestBody ${entityName} ${entityName?uncap_first}) {
        MsgJson msg=new MsgJson();
        try {
            msg.setData(${entityName?uncap_first}Service.save${entityName}(${entityName?uncap_first}));
            msg.setSuccess(true);
            msg.setMsg("添加记录成功！");
        } catch (Exception e) {
            
            e.printStackTrace();
            msg.setMsg("添加记录失败！");
        }
        return msg;
    }
    
    @ApiOperation(value = "部分数据添加", notes = "只对不为null的字段处理添加记录")
    @PostMapping(value = "/save${entityName}Selective")
    @ResponseBody
    public MsgJson save${entityName}Selective(${entityName} ${entityName?uncap_first}) {
        MsgJson msg=new MsgJson();
        try {
            msg.setData(${entityName?uncap_first}Service.save${entityName}Selective(${entityName?uncap_first}));
            msg.setSuccess(true);
            msg.setMsg("添加记录成功！");
        } catch (Exception e) {
            
            e.printStackTrace();
            msg.setMsg("添加记录失败！");
        }
        return msg;
    }
    
    /**
     * 
    * @Title: list${entityName}Condition      
    * @Description: 根据条件获取多条记录
    * @param ${entityName}Condition
    * @return MsgJson
    * @throws Exception
     */
    @ApiOperation(value = "获取多条记录", notes = "根据条件获取多条记录")
    @GetMapping(value = "/list${entityName}Condition")
    @ResponseBody
    public MsgJson list${entityName}Condition(${entityName}Condition ${entityName?uncap_first}Condition) throws Exception {
        int total = ${entityName?uncap_first}Service.count${entityName}Condition(${entityName?uncap_first}Condition);
        List<${entityName}> rows = ${entityName?uncap_first}Service.list${entityName}Condition(${entityName?uncap_first}Condition);
        MsgJson msg = new MsgJson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", rows);
		msg.setData(map);
		msg.setCode(1000);
		msg.setSuccess(true);
		return msg;
    }
    /**
     * 
    * @Title: remove${entityName}ByPrimaryKey     
    * @Description: 根据主键删除一条记录 
    * @param ${javaTablePK}
    * @return MsgJson
    * @throws Exception
     */
    @ApiOperation(value = "删除特定数据", notes = "根据主键删除一条记录")
    @DeleteMapping(value = "/remove${entityName}ByPrimaryKey/{${javaTablePK}}")
    @ResponseBody
    public MsgJson remove${entityName}ByPrimaryKey(@PathVariable("${javaTablePK}") String ${javaTablePK}) {
        MsgJson msg=new MsgJson();
        try {
            msg.setData(${entityName?uncap_first}Service.remove${entityName}ByPrimaryKey(${javaTablePK}));
            msg.setSuccess(true);
            msg.setMsg("删除记录成功！");
        } catch (Exception e) {
            
            e.printStackTrace();
            msg.setMsg("删除记录失败！");
        }
        return msg;
    }
    
    /**
     * 
    * @Title: removeList${entityName}ByPrimaryKey     
    * @Description: 根据主键批量删除记录 
    * @param ${javaTablePK}Array
    * @return MsgJson
    * @throws Exception
     */
    @ApiOperation(value = "批量删除特定数据", notes = "根据主键批量删除记录")
    @DeleteMapping(value = "/removeList${entityName}ByPrimaryKey")
    @ResponseBody
    public MsgJson removeList${entityName}ByPrimaryKey(String[] ${javaTablePK}Array) throws Exception{
        MsgJson msg=new MsgJson();
        try {
            msg.setData(${entityName?uncap_first}Service.removeList${entityName}ByPrimaryKey(${javaTablePK}Array));
            msg.setSuccess(true);
            msg.setMsg("批量删除记录成功！");
        } catch (Exception e) {
            
            e.printStackTrace();
            msg.setMsg("批量删除记录失败！");
        }
        return msg;
    }
    
    /**
     * 
    * @Title: update${entityName}ByPrimaryKey     
    * @Description: 根据主键更新一条记录（所有字段更新）
    * @param ${entityName}
    * @return MsgJson
    * @throws Exception
     */
    @ApiOperation(value = "全数据更新", notes = "根据主键更新一条记录（所有字段更新）")
    @PutMapping(value = "/update${entityName}ByPrimaryKey")
    @ResponseBody
    public MsgJson update${entityName}ByPrimaryKey(${entityName} ${entityName?uncap_first}) throws Exception{
        MsgJson msg=new MsgJson();
        try {
            msg.setData(${entityName?uncap_first}Service.update${entityName}ByPrimaryKey(${entityName?uncap_first}));
            msg.setSuccess(true);
            msg.setMsg("更新记录成功！");
        } catch (Exception e) {
            
            e.printStackTrace();
            msg.setMsg("更新记录失败！");
        }
        return msg;
    }
   
    /**
     * 
    * @Title: update${entityName}ByPrimaryKeySelective 
    * @Description: 根据主键更新一条记录（不为null的字段更新）
    * @param ${entityName}
    * @return MsgJson
    * @throws Exception
    */
    @ApiOperation(value = "部分数据更新", notes = "根据主键更新一条记录（不为null的字段更新）")
    @PutMapping(value = "/update${entityName}ByPrimaryKeySelective")
    @ResponseBody
    public MsgJson update${entityName}ByPrimaryKeySelective(${entityName} ${entityName?uncap_first}) throws Exception{
        MsgJson msg=new MsgJson();
        try {
            msg.setData(${entityName?uncap_first}Service.update${entityName}ByPrimaryKeySelective(${entityName?uncap_first}));
            msg.setSuccess(true);
            msg.setMsg("更新记录成功！");
        } catch (Exception e) {
            
            e.printStackTrace();
            msg.setMsg("更新记录失败！");
        }
        return msg;
    }
    
    /**
     * 
    * @Title: get${entityName}ByPrimaryKey  
    * @Description: 根据主键获取单条记录
    * @param ${entityName}Condition
    * @return MsgJson 
    * @throws Exception
     */
   @ApiOperation(value = "获取特定记录", notes = "根据主键获取单条记录")
   @GetMapping(value = "/get${entityName}ByPrimaryKey/{${javaTablePK}}")
   @ResponseBody
   public MsgJson get${entityName}ByPrimaryKey(@PathVariable("${javaTablePK}") String ${javaTablePK}) throws Exception{
        MsgJson msg=new MsgJson();
        try {
            msg.setData(${entityName?uncap_first}Service.get${entityName}ByPrimaryKey(${javaTablePK}));
            msg.setSuccess(true);
            msg.setMsg("获取记录成功！");
        } catch (Exception e) {
            
            e.printStackTrace();
            msg.setMsg("获取记录失败！");
        }
        return msg;
   }
    
}