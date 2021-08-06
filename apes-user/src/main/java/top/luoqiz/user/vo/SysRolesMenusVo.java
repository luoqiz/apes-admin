package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysRolesMenusEntity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
public class SysRolesMenusVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "菜单ID")
    @ExcelProperty(value = "菜单ID", index = 0)
    private Long menuId;


    @Schema(description = "角色ID")
    @ExcelProperty(value = "角色ID", index = 1)
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


    public static SysRolesMenusVo fromEntity(SysRolesMenusEntity entity) {
        if (entity == null) {
            return null;
        }
        SysRolesMenusVo vo = new SysRolesMenusVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<SysRolesMenusVo> fromEntity(List<SysRolesMenusEntity> entities) {
        LinkedList<SysRolesMenusVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysRolesMenusEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }


    @Override
    public String toString() {
        return "SysRolesMenus{" +
                "menuId=" + menuId +
                ", roleId=" + roleId +
                "}";
    }


}
