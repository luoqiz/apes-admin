package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysDeptEntity;
import top.luoqiz.user.queryCriteria.SysDeptQueryCriteria;
import top.luoqiz.user.service.SysDeptService;
import top.luoqiz.user.vo.SysDeptVo;

import java.util.List;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@Tag(name = "部门接口")
@RestController
@RequestMapping("sysDept")
public class SysDeptController {

    private SysDeptService sysDeptService;

    @Autowired
    public void setSysDeptService(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    /**
     * 部门根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysDeptVo>>
     */
    @Operation(summary = "部门分页查询")
    @GetMapping("search")
    public PageResponse<List<SysDeptVo>> search(SysDeptQueryCriteria criteria) {
        return sysDeptService.listSearch(criteria);
    }

    /**
     * 部门查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysDeptVo>
     */
    @Operation(summary = "部门查询所有记录")
    @GetMapping("/search/all")
    public List<SysDeptVo> searchAll(SysDeptQueryCriteria criteria) {
        return sysDeptService.listSearchAll(criteria);
    }

    /**
     * 部门获取记录详情
     *
     * @param id 主键
     * @return SysDeptVo
     */
    @Operation(summary = "部门获取记录详情")
    @GetMapping("/{id}")
    public SysDeptVo getById(@PathVariable Long id) {
        return sysDeptService.getVoById(id);
    }

    /**
     * 部门新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "部门新增数据")
    @PostMapping
    public boolean save(@RequestBody SysDeptEntity entity) {
        return sysDeptService.save(entity);
    }

    /**
     * 部门删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "部门删除数据")
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable String id) {
        return sysDeptService.removeById(id);
    }

    /**
     * 部门批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "部门批量删除数据")
    @DeleteMapping
    public boolean deleteById(@RequestBody List<String> ids) {
        return sysDeptService.removeByIds(ids);
    }

    /**
     * 部门更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "部门更新数据")
    @PutMapping
    public boolean updateById(@RequestBody SysDeptEntity entity) {
        return sysDeptService.updateById(entity);
    }

}