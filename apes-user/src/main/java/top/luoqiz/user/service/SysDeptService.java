package top.luoqiz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysDeptEntity;
import top.luoqiz.user.queryCriteria.SysDeptQueryCriteria;
import top.luoqiz.user.vo.SysDeptVo;

import java.util.List;

/**
 * <p>
 * 部门 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public interface SysDeptService extends IService<SysDeptEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysDeptVo>>
     */
    PageResponse<List<SysDeptVo>> listSearch(SysDeptQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysDeptVo>
     */
    List<SysDeptVo> listSearchAll(SysDeptQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysDeptVo
     */
    SysDeptVo getVoById(Long id);
}