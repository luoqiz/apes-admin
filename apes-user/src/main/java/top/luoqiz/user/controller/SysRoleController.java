package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysRoleEntity;
import top.luoqiz.user.queryCriteria.SysRoleQueryCriteria;
import top.luoqiz.user.service.SysRoleService;
import top.luoqiz.user.vo.SysRoleVo;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Tag(name = "角色表接口")
@RestController
@RequestMapping("sysRole")
public class SysRoleController {

    private SysRoleService sysRoleService;

    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    /**
     * 角色表根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysRoleVo>>
     */
    @Operation(summary = "角色表分页查询")
    @GetMapping("search")
    private PageResponse<List<SysRoleVo>> search(SysRoleQueryCriteria criteria) {
        return sysRoleService.listSearch(criteria);
    }

    /**
     * 角色表查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysRoleVo>
     */
    @Operation(summary = "角色表查询所有记录")
    @GetMapping("/search/all")
    private List<SysRoleVo> searchAll(SysRoleQueryCriteria criteria) {
        return sysRoleService.listSearchAll(criteria);
    }

    /**
     * 角色表获取记录详情
     *
     * @param id 主键
     * @return SysRoleVo
     */
    @Operation(summary = "角色表获取记录详情")
    @GetMapping("/{id}")
    private SysRoleVo getById(@PathVariable Long id) {
        return sysRoleService.getVoById(id);
    }

    /**
     * 角色表新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "角色表新增数据")
    @PostMapping
    private boolean save(@RequestBody SysRoleEntity entity) {
        return sysRoleService.save(entity);
    }

    /**
     * 角色表删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "角色表删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return sysRoleService.removeById(id);
    }

    /**
     * 角色表批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "角色表批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return sysRoleService.removeByIds(ids);
    }

    /**
     * 角色表更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "角色表更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysRoleEntity entity) {
        return sysRoleService.updateById(entity);
    }

}