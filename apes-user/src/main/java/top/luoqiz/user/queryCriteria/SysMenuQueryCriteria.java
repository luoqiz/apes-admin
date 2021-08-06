package top.luoqiz.user.queryCriteria;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-24
 */
public class SysMenuQueryCriteria extends BasePageCriteria {

    @Schema(description = "上级菜单ID")
    private Long pid;

    @Schema(description = "菜单类型")
    private Integer type;

    @Schema(description = "菜单标题")
    private String title;

    @Schema(description = "组件名称")
    private String name;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "是否外链")
    private Boolean iFrame;

    @Schema(description = "权限")
    private String permission;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }


    public Boolean getIFrame() {
        return iFrame;
    }

    public void setIFrame(Boolean iFrame) {
        this.iFrame = iFrame;
    }


    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


}