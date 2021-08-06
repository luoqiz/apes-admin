package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysUsersJobsEntity;
import top.luoqiz.user.mapper.SysUsersJobsMapper;
import top.luoqiz.user.queryCriteria.SysUsersJobsQueryCriteria;
import top.luoqiz.user.service.SysUsersJobsService;
import top.luoqiz.user.vo.SysUsersJobsVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Service
public class SysUsersJobsServiceImpl extends ServiceImpl<SysUsersJobsMapper, SysUsersJobsEntity> implements SysUsersJobsService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysUsersJobsVo>>
     */
    @Override
    public PageResponse<List<SysUsersJobsVo>> listSearch(SysUsersJobsQueryCriteria criteria) {
        LambdaQueryWrapper<SysUsersJobsEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(SysUsersJobsVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<SysUsersJobsVo>
     */
    @Override
    public List<SysUsersJobsVo> listSearchAll(SysUsersJobsQueryCriteria criteria) {
        LambdaQueryWrapper<SysUsersJobsEntity> queryWrapper = buildQuery(criteria);
        return SysUsersJobsVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysUsersJobsVo
     */
    @Override
    public SysUsersJobsVo getVoById(Long id) {
        SysUsersJobsEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysUsersJobsVo.fromEntity(resource);
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysUsersJobsEntity>
     */
    private LambdaQueryWrapper<SysUsersJobsEntity> buildQuery(SysUsersJobsQueryCriteria criteria) {

        LambdaQueryWrapper<SysUsersJobsEntity> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }
}