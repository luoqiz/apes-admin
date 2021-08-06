package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysJobEntity;
import top.luoqiz.user.queryCriteria.SysJobQueryCriteria;
import top.luoqiz.user.service.SysJobService;
import top.luoqiz.user.vo.SysJobVo;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-04
 */
@Tag(name = "接口")
@RestController
@RequestMapping("sysJob")
public class SysJobController {

    @Autowired
    private SysJobService sysJobService;

    /**
     * 根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysJobVo>>
     */
    @Operation(summary = "分页查询")
    @GetMapping("search")
    private PageResponse<List<SysJobVo>> search(SysJobQueryCriteria criteria) {
        return sysJobService.listSearch(criteria);
    }

    /**
     * 查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysJobVo>
     */
    @Operation(summary = "查询所有记录")
    @GetMapping("/search/all")
    private List<SysJobVo> searchAll(SysJobQueryCriteria criteria) {
        return sysJobService.listSearchAll(criteria);
    }

    /**
     * 获取记录详情
     *
     * @param id 主键
     * @return SysJobVo
     */
    @Operation(summary = "获取记录详情")
    @GetMapping("/{id}")
    private SysJobVo getById(@PathVariable Long id) {
        return sysJobService.getVoById(id);
    }

    /**
     * 新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "新增数据")
    @PostMapping
    private boolean save(@RequestBody SysJobEntity entity) {
        return sysJobService.save(entity);
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
        return sysJobService.removeById(id);
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
        return sysJobService.removeByIds(ids);
    }

    /**
     * 更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysJobEntity entity) {
        return sysJobService.updateById(entity);
    }

}