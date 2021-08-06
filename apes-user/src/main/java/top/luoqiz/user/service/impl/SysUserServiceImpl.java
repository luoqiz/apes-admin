package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysUserEntity;
import top.luoqiz.user.mapper.SysUserMapper;
import top.luoqiz.user.queryCriteria.SysUserQueryCriteria;
import top.luoqiz.user.service.SysUserService;
import top.luoqiz.user.vo.SysUserVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 系统用户 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-20
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysUserVo>>
     */
    @Override
    public PageResponse<List<SysUserVo>> listSearch(SysUserQueryCriteria criteria) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(SysUserVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<SysUserVo>
     */
    @Override
    public List<SysUserVo> listSearchAll(SysUserQueryCriteria criteria) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = buildQuery(criteria);
        return SysUserVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysUserVo
     */
    @Override
    public SysUserVo getVoById(Long id) {
        SysUserEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysUserVo.fromEntity(resource);
    }

    @Override
    public SysUserEntity getByUsername(String username) {
        LambdaQueryWrapper<SysUserEntity> query = new LambdaQueryWrapper<>();
        query.eq(SysUserEntity::getUsername, username);
        return getOne(query);
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysUserEntity>
     */
    private LambdaQueryWrapper<SysUserEntity> buildQuery(SysUserQueryCriteria criteria) {

        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getDeptId()).ifPresent(value -> queryWrapper.eq(SysUserEntity::getDeptId, value));
        Optional.ofNullable(criteria.getUsername()).ifPresent(value -> queryWrapper.like(SysUserEntity::getUsername, value));
        Optional.ofNullable(criteria.getNickName()).ifPresent(value -> queryWrapper.like(SysUserEntity::getNickName, value));
        Optional.ofNullable(criteria.getPhone()).ifPresent(value -> queryWrapper.like(SysUserEntity::getPhone, value));
        Optional.ofNullable(criteria.getIsAdmin()).ifPresent(value -> queryWrapper.eq(SysUserEntity::getIsAdmin, value));
        Optional.ofNullable(criteria.getEnabled()).ifPresent(value -> queryWrapper.eq(SysUserEntity::getEnabled, value));
        Optional.ofNullable(criteria.getNonLocked()).ifPresent(value -> queryWrapper.eq(SysUserEntity::getNonLocked, value));
        return queryWrapper;
    }
}