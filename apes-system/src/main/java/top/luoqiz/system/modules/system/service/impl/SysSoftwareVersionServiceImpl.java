package top.luoqiz.system.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.system.modules.system.entity.SysSoftwareVersionEntity;
import top.luoqiz.system.modules.system.queryCriteria.SysSoftwareVersionQueryCriteria;
import top.luoqiz.system.modules.system.vo.SysSoftwareVersionVo;
import top.luoqiz.system.modules.system.mapper.SysSoftwareVersionMapper;
import top.luoqiz.system.modules.system.service.SysSoftwareVersionService;

import java.util.*;

/**
 * <p>
 * 软件版本 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-05-21
 */
@Service
public class SysSoftwareVersionServiceImpl extends ServiceImpl<SysSoftwareVersionMapper, SysSoftwareVersionEntity> implements SysSoftwareVersionService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysSoftwareVersionVo>>
     */
    @Override
    public PageResponse<List<SysSoftwareVersionVo>> listSearch(SysSoftwareVersionQueryCriteria criteria) {
        LambdaQueryWrapper<SysSoftwareVersionEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        List<SysSoftwareVersionEntity> listEntity = baseMapper.selectListPage(queryWrapper, (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize());
        return PageResponse.build(SysSoftwareVersionVo.fromEntity(listEntity), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件
     * @return List<SysSoftwareVersionVo>
     */
    @Override
    public List<SysSoftwareVersionVo> listSearchAll(SysSoftwareVersionQueryCriteria criteria) {
        LambdaQueryWrapper<SysSoftwareVersionEntity> queryWrapper = buildQuery(criteria);
        return SysSoftwareVersionVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysSoftwareVersionVo
     */
    @Override
    public SysSoftwareVersionVo getVoById(Long id) {
        SysSoftwareVersionEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysSoftwareVersionVo.fromEntity(resource);
    }

    @Override
    public SysSoftwareVersionVo latest(String type) {
        LambdaQueryWrapper<SysSoftwareVersionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysSoftwareVersionEntity::getType, type);
        queryWrapper.orderByDesc(SysSoftwareVersionEntity::getCreateTime);
        queryWrapper.last("limit 0,1");
        return SysSoftwareVersionVo.fromEntity(baseMapper.selectOne(queryWrapper));
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysSoftwareVersionEntity>
     */
    private LambdaQueryWrapper<SysSoftwareVersionEntity> buildQuery(SysSoftwareVersionQueryCriteria criteria) {

        LambdaQueryWrapper<SysSoftwareVersionEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(criteria.getType())) {
            queryWrapper.eq(SysSoftwareVersionEntity::getType, criteria.getType());
        }
        Optional.ofNullable(criteria.getVersionName()).ifPresent(value -> queryWrapper.like(SysSoftwareVersionEntity::getVersionName, value));
        Optional.ofNullable(criteria.getCreateTime()).ifPresent(value -> {
            queryWrapper.lt(SysSoftwareVersionEntity::getCreateTime, value.toLocalDate().plusDays(1));
            queryWrapper.ge(SysSoftwareVersionEntity::getCreateTime, value.toLocalDate());
        });
        queryWrapper.orderByDesc(SysSoftwareVersionEntity::getCreateTime);
        return queryWrapper;
    }
}