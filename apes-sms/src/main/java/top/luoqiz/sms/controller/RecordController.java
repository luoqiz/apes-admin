package top.luoqiz.sms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.sms.entity.RecordEntity;
import top.luoqiz.sms.service.RecordService;
import top.luoqiz.sms.vo.RecordQueryCriteria;
import top.luoqiz.sms.vo.RecordVo;

import java.util.List;

/**
 * <p>
 * 短信记录表 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
@Tag(name = "短信记录表接口")
@RestController
@RequestMapping("record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 短信记录表根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < RecordVo>>
     */
    @Operation(summary = "短信记录表分页查询")
    @GetMapping("search")
    private PageResponse<List<RecordVo>> search(RecordQueryCriteria criteria) {
        return recordService.listSearch(criteria);
    }

    /**
     * 短信记录表查询所有记录
     *
     * @param criteria 查询条件
     * @return List<RecordVo>
     */
    @Operation(summary = "短信记录表查询所有记录")
    @GetMapping("/search/all")
    private List<RecordVo> searchAll(RecordQueryCriteria criteria) {
        return recordService.listSearchAll(criteria);
    }

    /**
     * 短信记录表获取记录详情
     *
     * @param id 主键
     * @return RecordVo
     */
    @Operation(summary = "短信记录表获取记录详情")
    @GetMapping("/{id}")
    private RecordVo getById(@PathVariable Long id) {
        return recordService.getVoById(id);
    }

    /**
     * 短信记录表新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "短信记录表新增数据")
    @PostMapping
    private boolean save(@RequestBody RecordEntity entity) {
        return recordService.save(entity);
    }

    /**
     * 短信记录表删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "短信记录表删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return recordService.removeById(id);
    }

    /**
     * 短信记录表批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "短信记录表批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return recordService.removeByIds(ids);
    }

    /**
     * 短信记录表更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "短信记录表更新数据")
    @PutMapping
    private boolean updateById(@RequestBody RecordEntity entity) {
        return recordService.updateById(entity);
    }

}