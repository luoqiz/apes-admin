package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysRolesDeptsEntity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
public class SysRolesDeptsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "", index = 0)
    private Long roleId;

    @ExcelProperty(value = "", index = 1)
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


    public static SysRolesDeptsVo fromEntity(SysRolesDeptsEntity entity) {
        if (entity == null) {
            return null;
        }
        SysRolesDeptsVo vo = new SysRolesDeptsVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<SysRolesDeptsVo> fromEntity(List<SysRolesDeptsEntity> entities) {
        LinkedList<SysRolesDeptsVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysRolesDeptsEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }


    @Override
    public String toString() {
        return "SysRolesDepts{" +
                "roleId=" + roleId +
                ", deptId=" + deptId +
                "}";
    }


}
