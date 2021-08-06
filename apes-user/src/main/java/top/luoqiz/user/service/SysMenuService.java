package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysMenuEntity;
import top.luoqiz.user.queryCriteria.SysMenuQueryCriteria;
import top.luoqiz.user.vo.SysMenuVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-24
 */
public interface SysMenuService extends IService<SysMenuEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysMenuVo>>
     */
    PageResponse<List<SysMenuVo>> listSearch(SysMenuQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysMenuVo>
     */
    List<SysMenuVo> listSearchAll(SysMenuQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysMenuVo
     */
    SysMenuVo getVoById(Long id);

    /**
     * 获取角色列表包含的所有菜单信息
     *
     * @param roleIds
     * @return
     */
    List<SysMenuVo> findListByRoleIds(Set<Long> roleIds);
}