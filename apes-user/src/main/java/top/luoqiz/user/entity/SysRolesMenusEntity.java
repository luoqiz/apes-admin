package top.luoqiz.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 角色菜单关联
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@TableName("sys_roles_menus")
@Schema(name = "SysRolesMenus对象", description = "角色菜单关联")
public class SysRolesMenusEntity extends Model<SysRolesMenusEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单ID")
    @TableId("menu_id")
    private Long menuId;

    @Schema(description = "角色ID")
    @TableField("role_id")
    private Long roleId;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    @Override
    public String toString() {
        return "SysRolesMenus{" +
               "menuId=" + menuId +
               ", roleId=" + roleId +
               "}";
    }

}
