package top.luoqiz.user.queryCriteria;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-04
 */
public class SysJobQueryCriteria extends BasePageCriteria {

    @Schema(description = "岗位名称")
    private String name;

    @Schema(description = "岗位状态")
    private Boolean enabled;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}