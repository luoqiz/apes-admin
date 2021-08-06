package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysRoleEntity;
import top.luoqiz.user.entity.SysUserEntity;
import top.luoqiz.user.mapper.SysRoleMapper;
import top.luoqiz.user.queryCriteria.SysRoleQueryCriteria;
import top.luoqiz.user.service.SysMenuService;
import top.luoqiz.user.service.SysRoleService;
import top.luoqiz.user.vo.SysMenuVo;
import top.luoqiz.user.vo.SysRoleVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    private SysMenuService menuService;

    @Autowired
    public void setMenuService(SysMenuService menuService) {
        this.menuService = menuService;
    }


    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysRoleVo>>
     */
    @Override
    public PageResponse<List<SysRoleVo>> listSearch(SysRoleQueryCriteria criteria) {
        LambdaQueryWrapper<SysRoleEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(SysRoleVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<SysRoleVo>
     */
    @Override
    public List<SysRoleVo> listSearchAll(SysRoleQueryCriteria criteria) {
        LambdaQueryWrapper<SysRoleEntity> queryWrapper = buildQuery(criteria);
        return SysRoleVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysRoleVo
     */
    @Override
    public SysRoleVo getVoById(Long id) {
        SysRoleEntity resource = getById(id);
        if (resource == null) {
            throw new BusinessException(ResponseCode.RESOURCE_NOT_FIND);
        }
        return SysRoleVo.fromEntity(resource);
    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(SysUserEntity user) {
        List<SysRoleVo> roles = findByUserId(user.getUserId());
        List<SysMenuVo> menuList = roles.isEmpty() ? new ArrayList<>() : menuService.findListByRoleIds(roles.stream().map(SysRoleVo::getRoleId).collect(Collectors.toSet()));
        Set<String> permissions = menuList.stream()
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(SysMenuVo::getPermission).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<SysRoleVo> findByUserId(Long userId) {
        return SysRoleVo.fromEntity(baseMapper.selectByUserId(userId));
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysRoleEntity>
     */
    private LambdaQueryWrapper<SysRoleEntity> buildQuery(SysRoleQueryCriteria criteria) {

        LambdaQueryWrapper<SysRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getName()).ifPresent(value -> queryWrapper.like(SysRoleEntity::getName, value));
        Optional.ofNullable(criteria.getLevel()).ifPresent(value -> queryWrapper.eq(SysRoleEntity::getLevel, value));
        Optional.ofNullable(criteria.getDescription()).ifPresent(value -> queryWrapper.like(SysRoleEntity::getDescription, value));
        Optional.ofNullable(criteria.getDataScope()).ifPresent(value -> queryWrapper.eq(SysRoleEntity::getDataScope, value));
        return queryWrapper;
    }
}