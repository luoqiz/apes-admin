package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysRolesMenusEntity;
import top.luoqiz.user.queryCriteria.SysRolesMenusQueryCriteria;
import top.luoqiz.user.vo.SysRolesMenusVo;

import java.util.List;

/**
 * <p>
 * 角色菜单关联 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public interface SysRolesMenusService extends IService<SysRolesMenusEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysRolesMenusVo>>
     */
    PageResponse<List<SysRolesMenusVo>> listSearch(SysRolesMenusQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysRolesMenusVo>
     */
    List<SysRolesMenusVo> listSearchAll(SysRolesMenusQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysRolesMenusVo
     */
    SysRolesMenusVo getVoById(Long id);
}