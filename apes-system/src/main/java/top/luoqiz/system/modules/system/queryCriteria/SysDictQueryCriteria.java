package top.luoqiz.system.modules.system.queryCriteria;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
public class SysDictQueryCriteria extends BasePageCriteria {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "描述")
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}