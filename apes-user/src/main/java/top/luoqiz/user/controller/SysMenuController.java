package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysMenuEntity;
import top.luoqiz.user.queryCriteria.SysMenuQueryCriteria;
import top.luoqiz.user.service.SysMenuService;
import top.luoqiz.user.vo.SysMenuVo;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-24
 */
@Tag(name = "菜单管理接口")
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysMenuVo>>
     */
    @Operation(summary = "分页查询")
    @GetMapping("search")
    private PageResponse<List<SysMenuVo>> search(SysMenuQueryCriteria criteria) {
        return sysMenuService.listSearch(criteria);
    }

    /**
     * 查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysMenuVo>
     */
    @Operation(summary = "查询所有记录")
    @GetMapping("/search/all")
    private List<SysMenuVo> searchAll(SysMenuQueryCriteria criteria) {
        return sysMenuService.listSearchAll(criteria);
    }

    /**
     * 获取记录详情
     *
     * @param id 主键
     * @return SysMenuVo
     */
    @Operation(summary = "获取记录详情")
    @GetMapping("/{id}")
    private SysMenuVo getById(@PathVariable Long id) {
        return sysMenuService.getVoById(id);
    }

    /**
     * 新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "新增数据")
    @PostMapping
    private boolean save(@RequestBody SysMenuEntity entity) {
        return sysMenuService.save(entity);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return sysMenuService.removeById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return sysMenuService.removeByIds(ids);
    }

    /**
     * 更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysMenuEntity entity) {
        return sysMenuService.updateById(entity);
    }

}