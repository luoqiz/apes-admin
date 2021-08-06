package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysMenuEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
public class SysMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long menuId;


    @Schema(description = "上级菜单ID")
    @ExcelProperty(value = "上级菜单ID", index = 1)
    private Long pid;


    @Schema(description = "子菜单数目")
    @ExcelProperty(value = "子菜单数目", index = 2)
    private Integer subCount;


    @Schema(description = "菜单类型")
    @ExcelProperty(value = "菜单类型", index = 3)
    private Integer type;


    @Schema(description = "菜单标题")
    @ExcelProperty(value = "菜单标题", index = 4)
    private String title;


    @Schema(description = "组件名称")
    @ExcelProperty(value = "组件名称", index = 5)
    private String name;


    @Schema(description = "组件")
    @ExcelProperty(value = "组件", index = 6)
    private String component;


    @Schema(description = "排序")
    @ExcelProperty(value = "排序", index = 7)
    private Integer menuSort;


    @Schema(description = "图标")
    @ExcelProperty(value = "图标", index = 8)
    private String icon;


    @Schema(description = "链接地址")
    @ExcelProperty(value = "链接地址", index = 9)
    private String path;


    @Schema(description = "是否外链")
    @ExcelProperty(value = "是否外链", index = 10)
    private Boolean iFrame;


    @Schema(description = "缓存")
    @ExcelProperty(value = "缓存", index = 11)
    private Boolean cache;


    @Schema(description = "隐藏")
    @ExcelProperty(value = "隐藏", index = 12)
    private Boolean hidden;


    @Schema(description = "权限")
    @ExcelProperty(value = "权限", index = 13)
    private String permission;


    @Schema(description = "创建者")
    @ExcelProperty(value = "创建者", index = 14)
    private String createBy;


    @Schema(description = "更新者")
    @ExcelProperty(value = "更新者", index = 15)
    private String updateBy;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 16)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 17)
    private LocalDateTime updateTime;

    @Schema(description = "子菜单数据")
    @ExcelProperty(value = "子菜单数据", index = 18)
    private List<SysMenuVo> children;


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


    public static SysMenuVo fromEntity(SysMenuEntity entity) {
        if (entity == null) {
            return null;
        }
        SysMenuVo vo = new SysMenuVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static List<SysMenuVo> fromEntity(List<SysMenuEntity> entities) {
        List<SysMenuVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysMenuEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
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


    public List<SysMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuVo> children) {
        this.children = children;
    }
}
