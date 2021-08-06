package top.luoqiz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysMenuEntity;
import top.luoqiz.user.mapper.SysMenuMapper;
import top.luoqiz.user.queryCriteria.SysMenuQueryCriteria;
import top.luoqiz.user.service.SysMenuService;
import top.luoqiz.user.vo.SysMenuVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-24
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysMenuVo>>
     */
    @Override
    public PageResponse<List<SysMenuVo>> listSearch(SysMenuQueryCriteria criteria) {
        LambdaQueryWrapper<SysMenuEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        List<SysMenuVo> menus = SysMenuVo.fromEntity(this.list(queryWrapper));
        List<SysMenuVo> resultMenus = new ArrayList<>(0);
        if (!menus.isEmpty()) {
            if (criteria.getPid() == null || criteria.getPid() == 0) {
                // 递归获取菜单树形结构
                // 获取父节点（根节点是0）
                resultMenus = menus.stream().filter(m -> m.getPid() == 0).peek(
                        (m) -> m.setChildren(getChildren(m, menus))
                ).collect(Collectors.toList());
            } else {
                resultMenus = menus;
            }
        }
        return PageResponse.build(resultMenus, total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<SysMenuVo>
     */
    @Override
    public List<SysMenuVo> listSearchAll(SysMenuQueryCriteria criteria) {
        LambdaQueryWrapper<SysMenuEntity> queryWrapper = buildQuery(criteria);
        List<SysMenuVo> menus = SysMenuVo.fromEntity(this.list(queryWrapper));
        List<SysMenuVo> resultMenus = new ArrayList<>(0);
        if (!menus.isEmpty()) {
            if (criteria.getPid() == null || criteria.getPid() == 0) {
                // 递归获取菜单树形结构
                // 获取父节点（根节点是0）
                resultMenus = menus.stream().filter(m -> m.getPid() == 0).peek(
                        (m) -> m.setChildren(getChildren(m, menus))
                ).collect(Collectors.toList());
            } else {
                resultMenus = menus;
            }
        }
        return resultMenus;
    }

    /**
     * 递归获取子节点
     **/
    private List<SysMenuVo> getChildren(SysMenuVo m, List<SysMenuVo> menus) {
        // 子节点parentId = 父节点ID
        List<SysMenuVo> childrenList = menus.stream().filter(m1 -> m.getMenuId().equals(m1.getPid()))
                .peek(m1 -> m1.setChildren(getChildren(m1, menus))).collect(Collectors.toList());
        return childrenList.isEmpty() ? null : childrenList;
    }


    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysMenuVo
     */
    @Override
    public SysMenuVo getVoById(Long id) {
        return SysMenuVo.fromEntity(getById(id));
    }

    @Override
    public List<SysMenuVo> findListByRoleIds(Set<Long> roleIds) {
        return SysMenuVo.fromEntity(baseMapper.selectListByRoleIds(roleIds));
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysMenuEntity>
     */
    private LambdaQueryWrapper<SysMenuEntity> buildQuery(SysMenuQueryCriteria criteria) {

        LambdaQueryWrapper<SysMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getPid()).ifPresent(value -> queryWrapper.eq(SysMenuEntity::getPid, value));
        Optional.ofNullable(criteria.getType()).ifPresent(value -> queryWrapper.eq(SysMenuEntity::getType, value));
        Optional.ofNullable(criteria.getTitle()).ifPresent(value -> queryWrapper.like(SysMenuEntity::getTitle, value));
        Optional.ofNullable(criteria.getName()).ifPresent(value -> queryWrapper.like(SysMenuEntity::getName, value));
        Optional.ofNullable(criteria.getComponent()).ifPresent(value -> queryWrapper.like(SysMenuEntity::getComponent, value));
        Optional.ofNullable(criteria.getIFrame()).ifPresent(value -> queryWrapper.eq(SysMenuEntity::getIFrame, value));
        Optional.ofNullable(criteria.getPermission()).ifPresent(value -> queryWrapper.eq(SysMenuEntity::getPermission, value));
        queryWrapper.orderByAsc(SysMenuEntity::getMenuSort);
        return queryWrapper;
    }
}