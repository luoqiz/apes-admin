package top.luoqiz.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

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
public class SysRoleEntity extends Model<SysRoleEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("role_id")
    private Long roleId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "角色级别")
    private Integer level;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "数据权限")
    private String dataScope;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
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
