package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysUsersRolesEntity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
public class SysUsersRolesVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "用户ID")
    @ExcelProperty(value = "用户ID", index = 0)
    private Long userId;


    @Schema(description = "角色ID")
    @ExcelProperty(value = "角色ID", index = 1)
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


    public static SysUsersRolesVo fromEntity(SysUsersRolesEntity entity) {
        if (entity == null) {
            return null;
        }
        SysUsersRolesVo vo = new SysUsersRolesVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<SysUsersRolesVo> fromEntity(List<SysUsersRolesEntity> entities) {
        LinkedList<SysUsersRolesVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysUsersRolesEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }


    @Override
    public String toString() {
        return "SysUsersRoles{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                "}";
    }


}
