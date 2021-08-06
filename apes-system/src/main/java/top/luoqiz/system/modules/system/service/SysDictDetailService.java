package top.luoqiz.system.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.system.modules.system.entity.SysDictDetailEntity;
import top.luoqiz.system.modules.system.queryCriteria.SysDictDetailQueryCriteria;
import top.luoqiz.system.modules.system.vo.SysDictDetailVo;

import java.util.List;

/**
 * <p>
 * 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
public interface SysDictDetailService extends IService<SysDictDetailEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysDictDetailVo>>
     */
    PageResponse<List<SysDictDetailVo>> listSearch(SysDictDetailQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysDictDetailVo>
     */
    List<SysDictDetailVo> listSearchAll(SysDictDetailQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysDictDetailVo
     */
    SysDictDetailVo getVoById(Long id);
}