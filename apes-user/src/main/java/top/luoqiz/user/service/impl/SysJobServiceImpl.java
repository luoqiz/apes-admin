package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysJobEntity;
import top.luoqiz.user.mapper.SysJobMapper;
import top.luoqiz.user.queryCriteria.SysJobQueryCriteria;
import top.luoqiz.user.service.SysJobService;
import top.luoqiz.user.vo.SysJobVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-04
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJobEntity> implements SysJobService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysJobVo>>
     */
    @Override
    public PageResponse<List<SysJobVo>> listSearch(SysJobQueryCriteria criteria) {
        LambdaQueryWrapper<SysJobEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(SysJobVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<SysJobVo>
     */
    @Override
    public List<SysJobVo> listSearchAll(SysJobQueryCriteria criteria) {
        LambdaQueryWrapper<SysJobEntity> queryWrapper = buildQuery(criteria);
        return SysJobVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysJobVo
     */
    @Override
    public SysJobVo getVoById(Long id) {
        SysJobEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysJobVo.fromEntity(resource);
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysJobEntity>
     */
    private LambdaQueryWrapper<SysJobEntity> buildQuery(SysJobQueryCriteria criteria) {

        LambdaQueryWrapper<SysJobEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getName()).ifPresent(value -> queryWrapper.like(SysJobEntity::getName, value));
        Optional.ofNullable(criteria.getEnabled()).ifPresent(value -> queryWrapper.eq(SysJobEntity::getEnabled, value));
        return queryWrapper;
    }
}