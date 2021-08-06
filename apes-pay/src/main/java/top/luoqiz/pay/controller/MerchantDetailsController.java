package top.luoqiz.pay.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.pay.entity.MerchantDetailsEntity;
import top.luoqiz.pay.vo.MerchantDetailsQueryCriteria;
import top.luoqiz.pay.service.MerchantDetailsService;
import top.luoqiz.pay.vo.MerchantDetailsVo;

import java.util.List;

/**
 * <p>
 * 支付应用信息 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-08
 */
@Tag(name = "支付应用信息接口")
@RestController
@RequestMapping("merchantDetails")
public class MerchantDetailsController {

    private MerchantDetailsService merchantDetailsService;

    @Autowired
    public void setMerchantDetailsService(MerchantDetailsService merchantDetailsService) {
        this.merchantDetailsService = merchantDetailsService;
    }

    /**
     * 支付应用信息根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < MerchantDetailsVo>>
     */
    @Operation(summary = "支付应用信息分页查询")
    @GetMapping("search")
    private PageResponse<List<MerchantDetailsVo>> search(MerchantDetailsQueryCriteria criteria) {
        return merchantDetailsService.listSearch(criteria);
    }

    /**
     * 支付应用信息查询所有记录
     *
     * @param criteria 查询条件
     * @return List<MerchantDetailsVo>
     */
    @Operation(summary = "支付应用信息查询所有记录")
    @GetMapping("/search/all")
    private List<MerchantDetailsVo> searchAll(MerchantDetailsQueryCriteria criteria) {
        return merchantDetailsService.listSearchAll(criteria);
    }

    /**
     * 支付应用信息获取记录详情
     *
     * @param id 主键
     * @return MerchantDetailsVo
     */
    @Operation(summary = "支付应用信息获取记录详情")
    @GetMapping("/{id}")
    private MerchantDetailsVo getById(@PathVariable String id) {
        return merchantDetailsService.getVoById(id);
    }

    /**
     * 支付应用信息新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "支付应用信息新增数据")
    @PostMapping
    private boolean save(@RequestBody MerchantDetailsEntity entity) {
        return merchantDetailsService.save(entity);
    }

    /**
     * 支付应用信息删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "支付应用信息删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return merchantDetailsService.removeById(id);
    }

    /**
     * 支付应用信息批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "支付应用信息批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return merchantDetailsService.removeByIds(ids);
    }

    /**
     * 支付应用信息更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "支付应用信息更新数据")
    @PutMapping
    private boolean updateById(@RequestBody MerchantDetailsEntity entity) {
        return merchantDetailsService.updateById(entity);
    }

}