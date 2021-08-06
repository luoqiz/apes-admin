package top.luoqiz.system.modules.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.annotation.AnonymousAccess;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.system.modules.system.entity.SysDictEntity;
import top.luoqiz.system.modules.system.queryCriteria.SysDictQueryCriteria;
import top.luoqiz.system.modules.system.service.SysDictService;
import top.luoqiz.system.modules.system.vo.SysDictVo;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
@Tag(name = "字典接口")
@RestController
@RequestMapping("SysDict")
public class SysDictController {

    private SysDictService sysDictService;

    @Autowired
    public void setSysDictService(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * 根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysDictVo>>
     */
    @AnonymousAccess
    @Operation(summary = "分页查询")
    @GetMapping("search")
    public PageResponse<List<SysDictVo>> search(SysDictQueryCriteria criteria) {
        return sysDictService.listSearch(criteria);
    }

    /**
     * 查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysDictVo>
     */
    @AnonymousAccess
    @Operation(summary = "查询所有记录")
    @GetMapping("/search/all")
    private List<SysDictVo> searchAll(SysDictQueryCriteria criteria) {
        return sysDictService.listSearchAll(criteria);
    }

    /**
     * 获取记录详情
     *
     * @param id 主键
     * @return SysDictVo
     */
    @AnonymousAccess
    @Operation(summary = "获取记录详情")
    @GetMapping("/{id}")
    private SysDictVo getById(@PathVariable Long id) {
        return sysDictService.getVoById(id);
    }

    /**
     * 新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "新增数据")
    @PostMapping
    private boolean save(@RequestBody SysDictEntity entity) {
        return sysDictService.save(entity);
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
        return sysDictService.removeById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "批量删除数据")
    @DeleteMapping
    private boolean deleteById(List<String> ids) {
        return sysDictService.removeByIds(ids);
    }

    /**
     * 更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysDictEntity entity) {
        return sysDictService.updateById(entity);
    }

}