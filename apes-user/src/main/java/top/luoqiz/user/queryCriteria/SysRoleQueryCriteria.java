package top.luoqiz.user.queryCriteria;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 角色表 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public class SysRoleQueryCriteria extends BasePageCriteria {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "角色级别")
    private Integer level;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "数据权限")
    private String dataScope;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }


}