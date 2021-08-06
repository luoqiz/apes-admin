<#list fileList as fileItem>
    <#if fileItem.current><#assign file=fileItem /></#if>
    <#if fileItem.fileName?ends_with("entity.java.ftl")><#assign entityFile=fileItem /></#if>
</#list>
package ${file.packagePath};

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import ${entityFile.packagePath}.${tableInfo.className}${entityFile.summary};

/**
 * <p>
 * ${tableInfo.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */
public interface ${tableInfo.className}${file.summary} extends BaseMapper<${tableInfo.className}${entityFile.summary}> {

    /**
     * 分页查询数据
     *
     * @param wrapper mybatis-plus 构造条件
     * @param offset  偏移量
     * @param size    数据量
     * @return List<${tableInfo.className}${entityFile.summary}>
     */
    List<${tableInfo.className}${entityFile.summary}> selectListPage(@Param("ew") Wrapper wrapper, @Param("offset") int offset, @Param("size") Integer size);

}
