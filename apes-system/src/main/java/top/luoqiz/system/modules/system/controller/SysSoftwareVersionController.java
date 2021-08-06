package top.luoqiz.system.modules.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.annotation.AnonymousAccess;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.system.modules.system.entity.SysSoftwareVersionEntity;
import top.luoqiz.system.modules.system.queryCriteria.SysSoftwareVersionQueryCriteria;
import top.luoqiz.system.modules.system.service.SysSoftwareVersionService;
import top.luoqiz.system.modules.system.vo.SysSoftwareVersionVo;

import java.util.List;

/**
 * <p>
 * 软件版本 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-05-21
 */
@Tag(name = "软件版本接口")
@RestController
@RequestMapping("sysSoftwareVersion")
public class SysSoftwareVersionController {

    private SysSoftwareVersionService sysSoftwareVersionService;

    @Autowired
    public void setSysSoftwareVersionService(SysSoftwareVersionService sysSoftwareVersionService) {
        this.sysSoftwareVersionService = sysSoftwareVersionService;
    }

    /**
     * 软件版本根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysSoftwareVersionVo>>
     */
    @AnonymousAccess
    @Operation(summary = "软件版本分页查询")
    @GetMapping("search")
    public PageResponse<List<SysSoftwareVersionVo>> search(SysSoftwareVersionQueryCriteria criteria) {
        return sysSoftwareVersionService.listSearch(criteria);
    }

    /**
     * 软件版本查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysSoftwareVersionVo>
     */
    @AnonymousAccess
    @Operation(summary = "软件版本查询所有记录")
    @GetMapping("/search/all")
    public List<SysSoftwareVersionVo> searchAll(SysSoftwareVersionQueryCriteria criteria) {
        return sysSoftwareVersionService.listSearchAll(criteria);
    }

    /**
     * 获取最新版本详情
     *
     * @return SysSoftwareVersionVo
     */
    @AnonymousAccess
    @Operation(summary = "软件版本获取记录详情")
    @GetMapping("/latest")
    public SysSoftwareVersionVo latest(String type) {
        return sysSoftwareVersionService.latest(type);
    }

    /**
     * 软件版本获取记录详情
     *
     * @param id 主键
     * @return SysSoftwareVersionVo
     */
    @AnonymousAccess
    @Operation(summary = "软件版本获取记录详情")
    @GetMapping("/{id}")
    public SysSoftwareVersionVo getById(@PathVariable Long id) {
        return sysSoftwareVersionService.getVoById(id);
    }

    /**
     * 软件版本新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "软件版本新增数据")
    @PostMapping
    public boolean save(@RequestBody SysSoftwareVersionEntity entity) {
        return sysSoftwareVersionService.save(entity);
    }

    /**
     * 软件版本删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "软件版本删除数据")
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable String id) {
        return sysSoftwareVersionService.removeById(id);
    }

    /**
     * 软件版本批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "软件版本批量删除数据")
    @DeleteMapping
    public boolean deleteById(@RequestBody List<String> ids) {
        return sysSoftwareVersionService.removeByIds(ids);
    }

    /**
     * 软件版本更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "软件版本更新数据")
    @PutMapping
    public boolean updateById(@RequestBody SysSoftwareVersionEntity entity) {
        return sysSoftwareVersionService.updateById(entity);
    }

}