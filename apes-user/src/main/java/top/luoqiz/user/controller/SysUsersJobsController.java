package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysUsersJobsEntity;
import top.luoqiz.user.queryCriteria.SysUsersJobsQueryCriteria;
import top.luoqiz.user.service.SysUsersJobsService;
import top.luoqiz.user.vo.SysUsersJobsVo;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Tag(name = "接口")
@RestController
@RequestMapping("sysUsersJobs")
public class SysUsersJobsController {

    private SysUsersJobsService sysUsersJobsService;

    @Autowired
    public void setSysUsersJobsService(SysUsersJobsService sysUsersJobsService) {
        this.sysUsersJobsService = sysUsersJobsService;
    }

    /**
     * 根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysUsersJobsVo>>
     */
    @Operation(summary = "分页查询")
    @GetMapping("search")
    private PageResponse<List<SysUsersJobsVo>> search(SysUsersJobsQueryCriteria criteria) {
        return sysUsersJobsService.listSearch(criteria);
    }

    /**
     * 查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysUsersJobsVo>
     */
    @Operation(summary = "查询所有记录")
    @GetMapping("/search/all")
    private List<SysUsersJobsVo> searchAll(SysUsersJobsQueryCriteria criteria) {
        return sysUsersJobsService.listSearchAll(criteria);
    }

    /**
     * 获取记录详情
     *
     * @param id 主键
     * @return SysUsersJobsVo
     */
    @Operation(summary = "获取记录详情")
    @GetMapping("/{id}")
    private SysUsersJobsVo getById(@PathVariable Long id) {
        return sysUsersJobsService.getVoById(id);
    }

    /**
     * 新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "新增数据")
    @PostMapping
    private boolean save(@RequestBody SysUsersJobsEntity entity) {
        return sysUsersJobsService.save(entity);
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
        return sysUsersJobsService.removeById(id);
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
        return sysUsersJobsService.removeByIds(ids);
    }

    /**
     * 更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "更新数据")
    @PutMapping
    private boolean updateById(@RequestBody SysUsersJobsEntity entity) {
        return sysUsersJobsService.updateById(entity);
    }

}