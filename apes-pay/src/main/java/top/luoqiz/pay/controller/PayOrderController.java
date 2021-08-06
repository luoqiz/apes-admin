package top.luoqiz.pay.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.pay.entity.PayOrderEntity;
import top.luoqiz.pay.vo.PayOrderVo;
import top.luoqiz.pay.service.PayOrderService;
import top.luoqiz.pay.vo.PayOrderQueryCriteria;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 支付订单记录表 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-16
 */
@Tag(name = "支付订单记录表接口")
@RestController
@RequestMapping("payOrder")
public class PayOrderController {

    private PayOrderService payOrderService;

    @Autowired
    public void setPayOrderService(PayOrderService payOrderService) {
        this.payOrderService = payOrderService;
    }

    /**
     * 支付订单记录表根据条件查询
     *
     * @param criteria 查询条件
     * @return PageResponse<List < PayOrderVo>>
     */
    @Operation(summary = "支付订单记录表分页查询")
    @GetMapping("search")
    private PageResponse<List<PayOrderVo>> search(PayOrderQueryCriteria criteria) {
        return payOrderService.listSearch(criteria);
    }

    /**
     * 支付订单记录表查询所有记录
     *
     * @param criteria 查询条件
     * @return List<PayOrderVo>
     */
    @Operation(summary = "支付订单记录表查询所有记录")
    @GetMapping("/search/all")
    private List<PayOrderVo> searchAll(PayOrderQueryCriteria criteria) {
        return payOrderService.listSearchAll(criteria);
    }

    /**
     * 支付订单记录表获取记录详情
     *
     * @param id 主键
     * @return PayOrderVo
     */
    @Operation(summary = "支付订单记录表获取记录详情")
    @GetMapping("/{id}")
    private PayOrderVo getById(@PathVariable Long id) {
        return payOrderService.getVoById(id);
    }

    /**
     * 支付订单记录表新增数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "支付订单记录表新增数据")
    @PostMapping
    private boolean save(@RequestBody PayOrderEntity entity) {
        entity.setExpiredTime(LocalDateTime.now().plusHours(1));
        return payOrderService.save(entity);
    }

    /**
     * 支付订单记录表删除数据
     *
     * @param id 主键id
     * @return boolean
     */
    @Operation(summary = "支付订单记录表删除数据")
    @DeleteMapping("/{id}")
    private boolean deleteById(@PathVariable String id) {
        return payOrderService.removeById(id);
    }

    /**
     * 支付订单记录表批量删除数据
     *
     * @param ids 主键id列表
     * @return boolean
     */
    @Operation(summary = "支付订单记录表批量删除数据")
    @DeleteMapping
    private boolean deleteById(@RequestBody List<String> ids) {
        return payOrderService.removeByIds(ids);
    }

    /**
     * 支付订单记录表更新数据
     *
     * @param entity 实体类
     * @return boolean
     */
    @Operation(summary = "支付订单记录表更新数据")
    @PutMapping
    private boolean updateById(@RequestBody PayOrderEntity entity) {
        return payOrderService.updateById(entity);
    }

}