package top.luoqiz.system.modules.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.annotation.AnonymousAccess;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.system.modules.system.entity.SysDictDetailEntity;
import top.luoqiz.system.modules.system.queryCriteria.SysDictDetailQueryCriteria;
import top.luoqiz.system.modules.system.service.SysDictDetailService;
import top.luoqiz.system.modules.system.vo.SysDictDetailVo;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
@Tag(name = "字典详情接口")
@RestController
@RequestMapping("SysDictDetail")
public class SysDictDetailController {

    private SysDictDetailService sysDictDetailService;

    @Autowired
    public void setSysDictDetailService(SysDictDetailService sysDictDetailService) {
        this.sysDictDetailService = sysDictDetailService;
    }

    /**
     * 根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysDictDetailVo>>
     */
    @AnonymousAccess
    @Operation(summary = "分页查询")
    @GetMapping("search")
    private PageResponse<List<SysDictDetailVo>> search(SysDictDetailQueryCriteria criteria) {
        return sysDictDetailService.listSearch(criteria);
    }

    /**
     * 查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysDictDetailVo>
     */
    @AnonymousAccess
    @Operation(summary = "查询所有记录")
    @GetMapping("/search/all")
    private List<SysDictDetailVo> searchAll(SysDictDetailQueryCriteria criteria) {
        return sysDictDetailService.listSearchAll(criteria);
    }

    /**
     * 获取记录详情
     *
     * @param id 主键
     * @return SysDictDetailVo
     */
    @AnonymousAccess
    @Operation(summary = "获取记录详情")
    @GetMapping("/{id}")
    private SysDictDetailVo getById(@PathVariable Long id) {
        return sysDictDetailService.getVoById(id);
    }

    /**
     * 新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "新增数据")
    @PostMapping
    private boolean save(@RequestBody SysDictDetailEntity entity) {
        return sysDictDetailService.save(entity);
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
        return sysDictDetailService.removeById(id);
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
        return sysDictDetailService.removeByIds(ids);
    }

    /**
     * 更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysDictDetailEntity entity) {
        return sysDictDetailService.updateById(entity);
    }

}