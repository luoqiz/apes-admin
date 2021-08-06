package top.luoqiz.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 用户角色关联
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@TableName("sys_users_roles")
@Schema(name = "SysUsersRoles对象", description = "用户角色关联")
public class SysUsersRolesEntity extends Model<SysUsersRolesEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    @TableId("user_id")
    private Long userId;

    @Schema(description = "角色ID")
    @TableField("role_id")
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    @Override
    public String toString() {
        return "SysUsersRoles{" +
               "userId=" + userId +
               ", roleId=" + roleId +
               "}";
    }

}
