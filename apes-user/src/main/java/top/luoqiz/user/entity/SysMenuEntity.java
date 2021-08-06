package top.luoqiz.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-24
 */
@TableName("sys_menu")
@Schema(name = "SysMenu对象", description = "")
public class SysMenuEntity extends Model<SysMenuEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("menu_id")
    private Long menuId;

    @Schema(description = "上级菜单ID")
    private Long pid;

    @Schema(description = "子菜单数目")
    private Integer subCount;

    @Schema(description = "菜单类型")
    private Integer type;

    @Schema(description = "菜单标题")
    private String title;

    @Schema(description = "组件名称")
    private String name;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "排序")
    private Integer menuSort;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "链接地址")
    private String path;

    @Schema(description = "是否外链")
    private Boolean iFrame;

    @Schema(description = "缓存")
    private Boolean cache;

    @Schema(description = "隐藏")
    private Boolean hidden;

    @Schema(description = "权限")
    private String permission;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
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

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIFrame() {
        return iFrame;
    }

    public void setIFrame(Boolean iFrame) {
        this.iFrame = iFrame;
    }

    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "SysMenu{" +
               "menuId=" + menuId +
               ", pid=" + pid +
               ", subCount=" + subCount +
               ", type=" + type +
               ", title=" + title +
               ", name=" + name +
               ", component=" + component +
               ", menuSort=" + menuSort +
               ", icon=" + icon +
               ", path=" + path +
               ", iFrame=" + iFrame +
               ", cache=" + cache +
               ", hidden=" + hidden +
               ", permission=" + permission +
               ", createBy=" + createBy +
               ", updateBy=" + updateBy +
               ", createTime=" + createTime +
               ", updateTime=" + updateTime +
               "}";
    }

}
