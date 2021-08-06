package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysRolesMenusEntity;
import top.luoqiz.user.queryCriteria.SysRolesMenusQueryCriteria;
import top.luoqiz.user.service.SysRolesMenusService;
import top.luoqiz.user.vo.SysRolesMenusVo;

import java.util.List;

/**
 * <p>
 * 角色菜单关联 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Tag(name = "角色菜单关联接口")
@RestController
@RequestMapping("sysRolesMenus")
public class SysRolesMenusController {

    private SysRolesMenusService sysRolesMenusService;

    @Autowired
    public void setSysRolesMenusService(SysRolesMenusService sysRolesMenusService) {
        this.sysRolesMenusService = sysRolesMenusService;
    }

    /**
     * 角色菜单关联根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysRolesMenusVo>>
     */
    @Operation(summary = "角色菜单关联分页查询")
    @GetMapping("search")
    private PageResponse<List<SysRolesMenusVo>> search(SysRolesMenusQueryCriteria criteria) {
        return sysRolesMenusService.listSearch(criteria);
    }

    /**
     * 角色菜单关联查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysRolesMenusVo>
     */
    @Operation(summary = "角色菜单关联查询所有记录")
    @GetMapping("/search/all")
    private List<SysRolesMenusVo> searchAll(SysRolesMenusQueryCriteria criteria) {
        return sysRolesMenusService.listSearchAll(criteria);
    }

    /**
     * 角色菜单关联获取记录详情
     *
     * @param id 主键
     * @return SysRolesMenusVo
     */
    @Operation(summary = "角色菜单关联获取记录详情")
    @GetMapping("/{id}")
    private SysRolesMenusVo getById(@PathVariable Long id) {
        return sysRolesMenusService.getVoById(id);
    }

    /**
     * 角色菜单关联新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "角色菜单关联新增数据")
    @PostMapping
    private boolean save(@RequestBody SysRolesMenusEntity entity) {
        return sysRolesMenusService.save(entity);
    }

    /**
     * 角色菜单关联删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "角色菜单关联删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return sysRolesMenusService.removeById(id);
    }

    /**
     * 角色菜单关联批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "角色菜单关联批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return sysRolesMenusService.removeByIds(ids);
    }

    /**
     * 角色菜单关联更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "角色菜单关联更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysRolesMenusEntity entity) {
        return sysRolesMenusService.updateById(entity);
    }

}