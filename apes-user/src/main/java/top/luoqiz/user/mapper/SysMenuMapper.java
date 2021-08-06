package top.luoqiz.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.luoqiz.user.entity.SysMenuEntity;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-24
 */
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    /**
     * 根据角色列表获取对应的菜单信息
     *
     * @param roleIds
     * @return
     */
    List<SysMenuEntity> selectListByRoleIds(@Param("roleIds") Set<Long> roleIds);
}
