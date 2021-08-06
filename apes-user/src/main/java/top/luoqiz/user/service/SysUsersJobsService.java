package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysUsersJobsEntity;
import top.luoqiz.user.queryCriteria.SysUsersJobsQueryCriteria;
import top.luoqiz.user.vo.SysUsersJobsVo;

import java.util.List;

/**
 * <p>
 * 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public interface SysUsersJobsService extends IService<SysUsersJobsEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysUsersJobsVo>>
     */
    PageResponse<List<SysUsersJobsVo>> listSearch(SysUsersJobsQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysUsersJobsVo>
     */
    List<SysUsersJobsVo> listSearchAll(SysUsersJobsQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysUsersJobsVo
     */
    SysUsersJobsVo getVoById(Long id);
}