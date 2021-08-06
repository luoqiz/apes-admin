package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysRolesDeptsEntity;
import top.luoqiz.user.queryCriteria.SysRolesDeptsQueryCriteria;
import top.luoqiz.user.vo.SysRolesDeptsVo;

import java.util.List;

/**
 * <p>
 * 角色部门关联 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public interface SysRolesDeptsService extends IService<SysRolesDeptsEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysRolesDeptsVo>>
     */
    PageResponse<List<SysRolesDeptsVo>> listSearch(SysRolesDeptsQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysRolesDeptsVo>
     */
    List<SysRolesDeptsVo> listSearchAll(SysRolesDeptsQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysRolesDeptsVo
     */
    SysRolesDeptsVo getVoById(Long id);
}