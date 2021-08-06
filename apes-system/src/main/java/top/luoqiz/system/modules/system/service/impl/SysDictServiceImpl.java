package top.luoqiz.system.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.system.modules.system.entity.SysDictEntity;
import top.luoqiz.system.modules.system.mapper.SysDictMapper;
import top.luoqiz.system.modules.system.queryCriteria.SysDictQueryCriteria;
import top.luoqiz.system.modules.system.service.SysDictService;
import top.luoqiz.system.modules.system.vo.SysDictVo;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements SysDictService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysDictVo>>
     */
    @Override
    public PageResponse<List<SysDictVo>> listSearch(SysDictQueryCriteria criteria) {
        LambdaQueryWrapper<SysDictEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(SysDictVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件
     * @return List<SysDictVo>
     */
    @Override
    public List<SysDictVo> listSearchAll(SysDictQueryCriteria criteria) {
        LambdaQueryWrapper<SysDictEntity> queryWrapper = buildQuery(criteria);
        return SysDictVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysDictVo
     */
    @Override
    public SysDictVo getVoById(Long id) {
        return SysDictVo.fromEntity(getById(id));
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysDictEntity>
     */
    private LambdaQueryWrapper<SysDictEntity> buildQuery(SysDictQueryCriteria criteria) {

        LambdaQueryWrapper<SysDictEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getName()).ifPresent(value -> queryWrapper.like(SysDictEntity::getName, value));
        Optional.ofNullable(criteria.getDescription()).ifPresent(value -> queryWrapper.like(SysDictEntity::getDescription, value));
        queryWrapper.orderByDesc(SysDictEntity::getUpdateTime);
        return queryWrapper;
    }
}