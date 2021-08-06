package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysUsersRolesEntity;
import top.luoqiz.user.mapper.SysUsersRolesMapper;
import top.luoqiz.user.queryCriteria.SysUsersRolesQueryCriteria;
import top.luoqiz.user.service.SysUsersRolesService;
import top.luoqiz.user.vo.SysUsersRolesVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色关联 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Service
public class SysUsersRolesServiceImpl extends ServiceImpl<SysUsersRolesMapper, SysUsersRolesEntity> implements SysUsersRolesService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysUsersRolesVo>>
     */
    @Override
    public PageResponse<List<SysUsersRolesVo>> listSearch(SysUsersRolesQueryCriteria criteria) {
        LambdaQueryWrapper<SysUsersRolesEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(SysUsersRolesVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构造
     * @return List<SysUsersRolesVo>
     */
    @Override
    public List<SysUsersRolesVo> listSearchAll(SysUsersRolesQueryCriteria criteria) {
        LambdaQueryWrapper<SysUsersRolesEntity> queryWrapper = buildQuery(criteria);
        return SysUsersRolesVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysUsersRolesVo
     */
    @Override
    public SysUsersRolesVo getVoById(Long id) {
        SysUsersRolesEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysUsersRolesVo.fromEntity(resource);
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysUsersRolesEntity>
     */
    private LambdaQueryWrapper<SysUsersRolesEntity> buildQuery(SysUsersRolesQueryCriteria criteria) {
        LambdaQueryWrapper<SysUsersRolesEntity> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }
}