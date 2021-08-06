package top.luoqiz.user.queryCriteria;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 部门 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public class SysDeptQueryCriteria extends BasePageCriteria {

    @Schema(description = "上级部门")
    private Long pid;

    @Schema(description = "状态")
    private Boolean enabled;


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}