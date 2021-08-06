package top.luoqiz.sms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.sms.entity.ConfigEntity;
import top.luoqiz.sms.service.ConfigService;
import top.luoqiz.sms.vo.ConfigQueryCriteria;
import top.luoqiz.sms.vo.ConfigVo;

import java.util.List;

/**
 * <p>
 * 短信配置表 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
@Tag(name = "短信配置表接口")
@RestController
@RequestMapping("config")
public class ConfigController {

    private ConfigService configService;

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * 短信配置表根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < ConfigVo>>
     */
    @Operation(summary = "短信配置表分页查询")
    @GetMapping("search")
    private PageResponse<List<ConfigVo>> search(ConfigQueryCriteria criteria) {
        return configService.listSearch(criteria);
    }

    /**
     * 短信配置表查询所有记录
     *
     * @param criteria 查询条件
     * @return List<ConfigVo>
     */
    @Operation(summary = "短信配置表查询所有记录")
    @GetMapping("/search/all")
    private List<ConfigVo> searchAll(ConfigQueryCriteria criteria) {
        return configService.listSearchAll(criteria);
    }

    /**
     * 短信配置表获取记录详情
     *
     * @param id 主键
     * @return ConfigVo
     */
    @Operation(summary = "短信配置表获取记录详情")
    @GetMapping("/{id}")
    private ConfigVo getById(@PathVariable Long id) {
        return configService.getVoById(id);
    }

    /**
     * 短信配置表新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "短信配置表新增数据")
    @PostMapping
    private boolean save(@RequestBody ConfigEntity entity) {
        return configService.save(entity);
    }

    /**
     * 短信配置表删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "短信配置表删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return configService.removeById(id);
    }

    /**
     * 短信配置表批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "短信配置表批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return configService.removeByIds(ids);
    }

    /**
     * 短信配置表更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "短信配置表更新数据")
    @PutMapping
    private boolean updateById(@RequestBody ConfigEntity entity) {
        return configService.updateById(entity);
    }

}