package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysRoleEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@TableName("sys_role")
@Schema(name = "SysRole对象", description = "角色表")
public class SysRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long roleId;


    @Schema(description = "名称")
    @ExcelProperty(value = "名称", index = 1)
    private String name;


    @Schema(description = "角色级别")
    @ExcelProperty(value = "角色级别", index = 2)
    private Integer level;


    @Schema(description = "描述")
    @ExcelProperty(value = "描述", index = 3)
    private String description;


    @Schema(description = "数据权限")
    @ExcelProperty(value = "数据权限", index = 4)
    private String dataScope;


    @Schema(description = "创建者")
    @ExcelProperty(value = "创建者", index = 5)
    private String createBy;


    @Schema(description = "更新者")
    @ExcelProperty(value = "更新者", index = 6)
    private String updateBy;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 7)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 8)
    private LocalDateTime updateTime;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

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


    public static SysRoleVo fromEntity(SysRoleEntity entity) {
        if (entity == null) {
            return null;
        }
        SysRoleVo vo = new SysRoleVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<SysRoleVo> fromEntity(List<SysRoleEntity> entities) {
        LinkedList<SysRoleVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysRoleEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }


    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", name=" + name +
                ", level=" + level +
                ", description=" + description +
                ", dataScope=" + dataScope +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }


}
