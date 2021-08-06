package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysJobEntity;
import top.luoqiz.user.queryCriteria.SysJobQueryCriteria;
import top.luoqiz.user.vo.SysJobVo;

import java.util.List;

/**
 * <p>
 * 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-04
 */
public interface SysJobService extends IService<SysJobEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysJobVo>>
     */
    PageResponse<List<SysJobVo>> listSearch(SysJobQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysJobVo>
     */
    List<SysJobVo> listSearchAll(SysJobQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysJobVo
     */
    SysJobVo getVoById(Long id);
}