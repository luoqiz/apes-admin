package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.GrantedAuthority;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysRoleEntity;
import top.luoqiz.user.entity.SysUserEntity;
import top.luoqiz.user.queryCriteria.SysRoleQueryCriteria;
import top.luoqiz.user.vo.SysRoleVo;

import java.util.List;

/**
 * <p>
 * 角色表 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public interface SysRoleService extends IService<SysRoleEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysRoleVo>>
     */
    PageResponse<List<SysRoleVo>> listSearch(SysRoleQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysRoleVo>
     */
    List<SysRoleVo> listSearchAll(SysRoleQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysRoleVo
     */
    SysRoleVo getVoById(Long id);

    /**
     * 获取用户权限信息
     *
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(SysUserEntity user);


    /**
     * 获取用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<SysRoleVo> findByUserId(Long userId);
}