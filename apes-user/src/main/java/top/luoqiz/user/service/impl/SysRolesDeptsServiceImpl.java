package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysRolesDeptsEntity;
import top.luoqiz.user.mapper.SysRolesDeptsMapper;
import top.luoqiz.user.queryCriteria.SysRolesDeptsQueryCriteria;
import top.luoqiz.user.service.SysRolesDeptsService;
import top.luoqiz.user.vo.SysRolesDeptsVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色部门关联 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Service
public class SysRolesDeptsServiceImpl extends ServiceImpl<SysRolesDeptsMapper, SysRolesDeptsEntity> implements SysRolesDeptsService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysRolesDeptsVo>>
     */
    @Override
    public PageResponse<List<SysRolesDeptsVo>> listSearch(SysRolesDeptsQueryCriteria criteria) {
        LambdaQueryWrapper<SysRolesDeptsEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(SysRolesDeptsVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<SysRolesDeptsVo>
     */
    @Override
    public List<SysRolesDeptsVo> listSearchAll(SysRolesDeptsQueryCriteria criteria) {
        LambdaQueryWrapper<SysRolesDeptsEntity> queryWrapper = buildQuery(criteria);
        return SysRolesDeptsVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysRolesDeptsVo
     */
    @Override
    public SysRolesDeptsVo getVoById(Long id) {
        SysRolesDeptsEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysRolesDeptsVo.fromEntity(resource);
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysRolesDeptsEntity>
     */
    private LambdaQueryWrapper<SysRolesDeptsEntity> buildQuery(SysRolesDeptsQueryCriteria criteria) {

        LambdaQueryWrapper<SysRolesDeptsEntity> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }
}