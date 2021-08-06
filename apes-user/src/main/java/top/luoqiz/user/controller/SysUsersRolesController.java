package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysUsersRolesEntity;
import top.luoqiz.user.queryCriteria.SysUsersRolesQueryCriteria;
import top.luoqiz.user.service.SysUsersRolesService;
import top.luoqiz.user.vo.SysUsersRolesVo;

import java.util.List;

/**
 * <p>
 * 用户角色关联 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Tag(name = "用户角色关联接口")
@RestController
@RequestMapping("sysUsersRoles")
public class SysUsersRolesController {

    private SysUsersRolesService sysUsersRolesService;

    @Autowired
    public void setSysUsersRolesService(SysUsersRolesService sysUsersRolesService) {
        this.sysUsersRolesService = sysUsersRolesService;
    }

    /**
     * 用户角色关联根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysUsersRolesVo>>
     */
    @Operation(summary = "用户角色关联分页查询")
    @GetMapping("search")
    private PageResponse<List<SysUsersRolesVo>> search(SysUsersRolesQueryCriteria criteria) {
        return sysUsersRolesService.listSearch(criteria);
    }

    /**
     * 用户角色关联查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysUsersRolesVo>
     */
    @Operation(summary = "用户角色关联查询所有记录")
    @GetMapping("/search/all")
    private List<SysUsersRolesVo> searchAll(SysUsersRolesQueryCriteria criteria) {
        return sysUsersRolesService.listSearchAll(criteria);
    }

    /**
     * 用户角色关联获取记录详情
     *
     * @param id 主键
     * @return SysUsersRolesVo
     */
    @Operation(summary = "用户角色关联获取记录详情")
    @GetMapping("/{id}")
    private SysUsersRolesVo getById(@PathVariable Long id) {
        return sysUsersRolesService.getVoById(id);
    }

    /**
     * 用户角色关联新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "用户角色关联新增数据")
    @PostMapping
    private boolean save(@RequestBody SysUsersRolesEntity entity) {
        return sysUsersRolesService.save(entity);
    }

    /**
     * 用户角色关联删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "用户角色关联删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return sysUsersRolesService.removeById(id);
    }

    /**
     * 用户角色关联批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "用户角色关联批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return sysUsersRolesService.removeByIds(ids);
    }

    /**
     * 用户角色关联更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "用户角色关联更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysUsersRolesEntity entity) {
        return sysUsersRolesService.updateById(entity);
    }

}