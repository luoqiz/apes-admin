<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("queryCriteria.java.ftl")><#assign criteriaFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("service.java.ftl")><#assign iserviceFile=fileItem /></#if>
    <#if fileItem.fileName?ends_with("vo.java.ftl")><#assign voFile=fileItem /></#if>
</#list>
package ${file.packagePath};

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import top.luoqiz.common.web.response.vo.PageResponse;
import ${entityFile.packagePath}.${tableInfo.className}${entityFile.summary};
import ${voFile.packagePath}.${tableInfo.className}${voFile.summary};
import ${iserviceFile.packagePath}.${tableInfo.className}${iserviceFile.summary};
import ${criteriaFile.packagePath}.${tableInfo.className}${criteriaFile.summary};

import java.util.List;

/**
 * <p>
 * ${tableInfo.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */
<#if swagger3>
@Tag(name = "${tableInfo.comment!}接口")
</#if>
@RestController
@RequestMapping("${tableInfo.className?uncap_first}")
public class ${tableInfo.className}${file.summary} {

    private ${tableInfo.className}${iserviceFile.summary} ${tableInfo.className?uncap_first}${iserviceFile.summary};

    @Autowired
    public void set${tableInfo.className}${iserviceFile.summary}(${tableInfo.className}${iserviceFile.summary} ${tableInfo.className?uncap_first}${iserviceFile.summary}) {
        this.${tableInfo.className?uncap_first}${iserviceFile.summary} = ${tableInfo.className?uncap_first}${iserviceFile.summary};
    }

    /**
     * ${tableInfo.comment!}根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List<${tableInfo.className}${voFile.summary}>>
     */
    <#if swagger3>
    @Operation(summary = "${tableInfo.comment!}分页查询")
    </#if>
    @GetMapping("search")
    public PageResponse<List<${tableInfo.className}${voFile.summary}>> search(${tableInfo.className}${criteriaFile.summary} criteria) {
        return ${tableInfo.className?uncap_first}${iserviceFile.summary}.listSearch(criteria);
    }

    /**
     * ${tableInfo.comment!}查询所有记录
     *
     * @param criteria 查询条件
     * @return List<${tableInfo.className}${voFile.summary}>
     */
    <#if swagger3>
    @Operation(summary = "${tableInfo.comment!}查询所有记录")
    </#if>
    @GetMapping("/search/all")
    public List<${tableInfo.className}${voFile.summary}> searchAll(${tableInfo.className}${criteriaFile.summary} criteria) {
        return ${tableInfo.className?uncap_first}${iserviceFile.summary}.listSearchAll(criteria);
    }

    /**
     * ${tableInfo.comment!}获取记录详情
     *
     * @param id 主键
     * @return ${tableInfo.className}${voFile.summary}
     */
    <#if swagger3>
    @Operation(summary = "${tableInfo.comment!}获取记录详情")
    </#if>
    @GetMapping("/{id}")
    public ${tableInfo.className}${voFile.summary} getById(@PathVariable <#list tableInfo.colInfoList as field><#if field.primaryKey>${field.javaColumnType}</#if></#list> id) {
        return ${tableInfo.className?uncap_first}${iserviceFile.summary}.getVoById(id);
    }

    /**
     * ${tableInfo.comment!}新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    <#if swagger3>
    @Operation(summary = "${tableInfo.comment!}新增数据")
    </#if>
    @PostMapping
    public boolean save(@RequestBody ${tableInfo.className}${entityFile.summary} entity) {
        return ${tableInfo.className?uncap_first}${iserviceFile.summary}.save(entity);
    }

    /**
     * ${tableInfo.comment!}删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    <#if swagger3>
    @Operation(summary = "${tableInfo.comment!}删除数据")
    </#if>
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable String id) {
        return ${tableInfo.className?uncap_first}${iserviceFile.summary}.removeById(id);
    }

    /**
     * ${tableInfo.comment!}批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    <#if swagger3>
    @Operation(summary = "${tableInfo.comment!}批量删除数据")
    </#if>
    @DeleteMapping
    public boolean deleteById(@RequestBody List<String> ids) {
        return ${tableInfo.className?uncap_first}${iserviceFile.summary}.removeByIds(ids);
    }

    /**
     * ${tableInfo.comment!}更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    <#if swagger3>
    @Operation(summary = "${tableInfo.comment!}更新数据")
    </#if>
    @PutMapping
    public boolean updateById(@RequestBody ${tableInfo.className}${entityFile.summary} entity) {
        return ${tableInfo.className?uncap_first}${iserviceFile.summary}.updateById(entity);
    }

}