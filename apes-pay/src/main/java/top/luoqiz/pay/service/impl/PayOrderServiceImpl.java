package top.luoqiz.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.pay.entity.PayOrderEntity;
import top.luoqiz.pay.vo.PayOrderQueryCriteria;
import top.luoqiz.pay.vo.PayOrderVo;
import top.luoqiz.pay.mapper.PayOrderMapper;
import top.luoqiz.pay.service.PayOrderService;

import java.util.*;

/**
 * <p>
 * 支付订单记录表 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-16
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrderEntity> implements PayOrderService  {
    /**
    * 条件查询分页列表
    *
    * @param criteria 查询条件构建类
    * @return PageResponse<List<PayOrderVo>>
    */
    @Override
    public PageResponse<List<PayOrderVo>> listSearch(PayOrderQueryCriteria criteria) {
        LambdaQueryWrapper<PayOrderEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(PayOrderVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
    * 条件查询全部列表
    *
    * @param criteria
    * @return List<PayOrderVo>
    */
    @Override
    public List<PayOrderVo> listSearchAll(PayOrderQueryCriteria criteria){
        LambdaQueryWrapper<PayOrderEntity> queryWrapper = buildQuery(criteria);
        return PayOrderVo.fromEntity(this.list(queryWrapper));
    }

    /**
    * 根据主键查询记录
    *
    * @param id 主键
    * @return PayOrderVo
    */
    @Override
    public PayOrderVo getVoById(Long id) {
        return PayOrderVo.fromEntity(getById(id));
    }

    /**
    * 构建查询实体类
    *
    * @param criteria 查询条件
    * @return LambdaQueryWrapper<PayOrderEntity>
    */
    private LambdaQueryWrapper<PayOrderEntity> buildQuery(PayOrderQueryCriteria criteria) {

        LambdaQueryWrapper<PayOrderEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getTitle()).ifPresent(value -> queryWrapper.like(PayOrderEntity::getTitle, value));
        Optional.ofNullable(criteria.getPayType()).ifPresent(value -> queryWrapper.eq(PayOrderEntity::getPayType, value));
        Optional.ofNullable(criteria.getPayWay()).ifPresent(value -> queryWrapper.eq(PayOrderEntity::getPayWay, value));
        return queryWrapper;
    }
}