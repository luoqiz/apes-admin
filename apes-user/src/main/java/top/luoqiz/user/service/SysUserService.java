package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysUserEntity;
import top.luoqiz.user.queryCriteria.SysUserQueryCriteria;
import top.luoqiz.user.vo.SysUserVo;

import java.util.List;

/**
 * <p>
 * 系统用户 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-20
 */
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysUserVo>>
     */
    PageResponse<List<SysUserVo>> listSearch(SysUserQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysUserVo>
     */
    List<SysUserVo> listSearchAll(SysUserQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysUserVo
     */
    SysUserVo getVoById(Long id);

    SysUserEntity getByUsername(String username);
}