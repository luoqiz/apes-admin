package top.luoqiz.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 角色部门关联
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@TableName("sys_roles_depts")
@Schema(name = "SysRolesDepts对象", description = "角色部门关联")
public class SysRolesDeptsEntity extends Model<SysRolesDeptsEntity> {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
    private Long roleId;

    @TableField("dept_id")
    private Long deptId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


    @Override
    public String toString() {
        return "SysRolesDepts{" +
               "roleId=" + roleId +
               ", deptId=" + deptId +
               "}";
    }

}
