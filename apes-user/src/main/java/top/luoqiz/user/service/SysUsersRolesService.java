package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysUsersRolesEntity;
import top.luoqiz.user.queryCriteria.SysUsersRolesQueryCriteria;
import top.luoqiz.user.vo.SysUsersRolesVo;

import java.util.List;

/**
 * <p>
 * 用户角色关联 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public interface SysUsersRolesService extends IService<SysUsersRolesEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysUsersRolesVo>>
     */
    PageResponse<List<SysUsersRolesVo>> listSearch(SysUsersRolesQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysUsersRolesVo>
     */
    List<SysUsersRolesVo> listSearchAll(SysUsersRolesQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysUsersRolesVo
     */
    SysUsersRolesVo getVoById(Long id);
}