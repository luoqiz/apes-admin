package top.luoqiz.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.user.entity.SysUserEntity;
import top.luoqiz.user.queryCriteria.SysUserQueryCriteria;
import top.luoqiz.user.service.SysUserService;
import top.luoqiz.user.vo.SysUserVo;

import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-20
 */
@Tag(name = "系统用户接口")
@RestController
@RequestMapping("sysUser")
public class SysUserController {

    private SysUserService sysUserService;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 系统用户根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < SysUserVo>>
     */
    @Operation(summary = "系统用户分页查询")
    @GetMapping("search")
    public PageResponse<List<SysUserVo>> search(SysUserQueryCriteria criteria) {
        return sysUserService.listSearch(criteria);
    }

    /**
     * 系统用户查询所有记录
     *
     * @param criteria 查询条件
     * @return List<SysUserVo>
     */
    @Operation(summary = "系统用户查询所有记录")
    @GetMapping("/search/all")
    public List<SysUserVo> searchAll(SysUserQueryCriteria criteria) {
        return sysUserService.listSearchAll(criteria);
    }

    /**
     * 系统用户获取记录详情
     *
     * @param id 主键
     * @return SysUserVo
     */
    @Operation(summary = "系统用户获取记录详情")
    @GetMapping("/{id}")
    public SysUserVo getById(@PathVariable Long id) {
        return sysUserService.getVoById(id);
    }

    /**
     * 系统用户新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "系统用户新增数据")
    @PostMapping
    public boolean save(@RequestBody SysUserEntity entity) {
        return sysUserService.save(entity);
    }

    /**
     * 系统用户删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "系统用户删除数据")
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable String id) {
        return sysUserService.removeById(id);
    }

    /**
     * 系统用户批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "系统用户批量删除数据")
    @DeleteMapping
    public boolean deleteById(@RequestBody List<String> ids) {
        return sysUserService.removeByIds(ids);
    }

    /**
     * 系统用户更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "系统用户更新数据")
    @PutMapping
    public boolean updateById(@RequestBody SysUserEntity entity) {
        return sysUserService.updateById(entity);
    }

}