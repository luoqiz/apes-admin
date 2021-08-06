package top.luoqiz.system.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.system.modules.system.entity.SysDictEntity;
import top.luoqiz.system.modules.system.queryCriteria.SysDictQueryCriteria;
import top.luoqiz.system.modules.system.vo.SysDictVo;

import java.util.List;

/**
 * <p>
 * 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
public interface SysDictService extends IService<SysDictEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysDictVo>>
     */
    PageResponse<List<SysDictVo>> listSearch(SysDictQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysDictVo>
     */
    List<SysDictVo> listSearchAll(SysDictQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysDictVo
     */
    SysDictVo getVoById(Long id);
}