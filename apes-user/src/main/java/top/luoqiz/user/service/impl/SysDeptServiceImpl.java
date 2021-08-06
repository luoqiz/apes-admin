package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysDeptEntity;
import top.luoqiz.user.mapper.SysDeptMapper;
import top.luoqiz.user.queryCriteria.SysDeptQueryCriteria;
import top.luoqiz.user.service.SysDeptService;
import top.luoqiz.user.vo.SysDeptVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 部门 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements SysDeptService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysDeptVo>>
     */
    @Override
    public PageResponse<List<SysDeptVo>> listSearch(SysDeptQueryCriteria criteria) {
        LambdaQueryWrapper<SysDeptEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        List<SysDeptEntity> listEntity = baseMapper.selectListPage(queryWrapper, (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize());
        return PageResponse.build(SysDeptVo.fromEntity(listEntity), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<SysDeptVo>
     */
    @Override
    public List<SysDeptVo> listSearchAll(SysDeptQueryCriteria criteria) {
        LambdaQueryWrapper<SysDeptEntity> queryWrapper = buildQuery(criteria);
        return SysDeptVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysDeptVo
     */
    @Override
    public SysDeptVo getVoById(Long id) {
        SysDeptEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysDeptVo.fromEntity(resource);
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysDeptEntity>
     */
    private LambdaQueryWrapper<SysDeptEntity> buildQuery(SysDeptQueryCriteria criteria) {

        LambdaQueryWrapper<SysDeptEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getPid()).ifPresent(value -> queryWrapper.eq(SysDeptEntity::getPid, value));
        Optional.ofNullable(criteria.getEnabled()).ifPresent(value -> queryWrapper.eq(SysDeptEntity::getEnabled, value));
        return queryWrapper;
    }
}