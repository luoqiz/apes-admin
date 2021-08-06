package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysRolesMenusEntity;
import top.luoqiz.user.mapper.SysRolesMenusMapper;
import top.luoqiz.user.queryCriteria.SysRolesMenusQueryCriteria;
import top.luoqiz.user.service.SysRolesMenusService;
import top.luoqiz.user.vo.SysRolesMenusVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色菜单关联 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Service
public class SysRolesMenusServiceImpl extends ServiceImpl<SysRolesMenusMapper, SysRolesMenusEntity> implements SysRolesMenusService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysRolesMenusVo>>
     */
    @Override
    public PageResponse<List<SysRolesMenusVo>> listSearch(SysRolesMenusQueryCriteria criteria) {
        LambdaQueryWrapper<SysRolesMenusEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(SysRolesMenusVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<SysRolesMenusVo>
     */
    @Override
    public List<SysRolesMenusVo> listSearchAll(SysRolesMenusQueryCriteria criteria) {
        LambdaQueryWrapper<SysRolesMenusEntity> queryWrapper = buildQuery(criteria);
        return SysRolesMenusVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysRolesMenusVo
     */
    @Override
    public SysRolesMenusVo getVoById(Long id) {
        SysRolesMenusEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysRolesMenusVo.fromEntity(resource);
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysRolesMenusEntity>
     */
    private LambdaQueryWrapper<SysRolesMenusEntity> buildQuery(SysRolesMenusQueryCriteria criteria) {

        LambdaQueryWrapper<SysRolesMenusEntity> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }
}