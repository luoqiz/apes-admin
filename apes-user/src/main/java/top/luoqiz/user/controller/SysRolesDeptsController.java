package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysRolesDeptsEntity;
import top.luoqiz.user.queryCriteria.SysRolesDeptsQueryCriteria;
import top.luoqiz.user.service.SysRolesDeptsService;
import top.luoqiz.user.vo.SysRolesDeptsVo;

import java.util.List;

/**
 * <p>
 * 角色部门关联 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Tag(name = "角色部门关联接口")
@RestController
@RequestMapping("sysRolesDepts")
public class SysRolesDeptsController {

    private SysRolesDeptsService sysRolesDeptsService;

    @Autowired
    public void setSysRolesDeptsService(SysRolesDeptsService sysRolesDeptsService) {
        this.sysRolesDeptsService = sysRolesDeptsService;
    }

    /**
     * 角色部门关联根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysRolesDeptsVo>>
     */
    @Operation(summary = "角色部门关联分页查询")
    @GetMapping("search")
    private PageResponse<List<SysRolesDeptsVo>> search(SysRolesDeptsQueryCriteria criteria) {
        return sysRolesDeptsService.listSearch(criteria);
    }

    /**
     * 角色部门关联查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysRolesDeptsVo>
     */
    @Operation(summary = "角色部门关联查询所有记录")
    @GetMapping("/search/all")
    private List<SysRolesDeptsVo> searchAll(SysRolesDeptsQueryCriteria criteria) {
        return sysRolesDeptsService.listSearchAll(criteria);
    }

    /**
     * 角色部门关联获取记录详情
     *
     * @param id 主键
     * @return SysRolesDeptsVo
     */
    @Operation(summary = "角色部门关联获取记录详情")
    @GetMapping("/{id}")
    private SysRolesDeptsVo getById(@PathVariable Long id) {
        return sysRolesDeptsService.getVoById(id);
    }

    /**
     * 角色部门关联新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "角色部门关联新增数据")
    @PostMapping
    private boolean save(@RequestBody SysRolesDeptsEntity entity) {
        return sysRolesDeptsService.save(entity);
    }

    /**
     * 角色部门关联删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "角色部门关联删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return sysRolesDeptsService.removeById(id);
    }

    /**
     * 角色部门关联批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "角色部门关联批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return sysRolesDeptsService.removeByIds(ids);
    }

    /**
     * 角色部门关联更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "角色部门关联更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysRolesDeptsEntity entity) {
        return sysRolesDeptsService.updateById(entity);
    }

}