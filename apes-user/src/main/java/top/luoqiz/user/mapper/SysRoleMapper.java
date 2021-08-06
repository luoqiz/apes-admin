package top.luoqiz.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.luoqiz.user.entity.SysRoleEntity;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 获取用户拥有的角色信息
     *
     * @param userId
     * @return
     */
    List<SysRoleEntity> selectByUserId(@Param("userId") Long userId);
}
