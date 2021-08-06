package top.luoqiz.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.pay.entity.PayOrderEntity;
import top.luoqiz.pay.vo.PayOrderQueryCriteria;
import top.luoqiz.pay.vo.PayOrderVo;

import java.util.*;

/**
 * <p>
 * 支付订单记录表 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-16
 */
public interface PayOrderService extends IService<PayOrderEntity> {
    /**
    * 条件查询分页列表
    *
    * @param criteria 查询条件构建类
    * @return PageResponse<List<PayOrderVo>>
    */
    PageResponse<List<PayOrderVo>> listSearch(PayOrderQueryCriteria criteria);

    /**
    * 条件查询全部列表
    *
    * @param criteria 查询条件构建类
    * @return List<PayOrderVo>
    */
    List<PayOrderVo> listSearchAll(PayOrderQueryCriteria criteria);

    /**
    * 根据主键获取记录
    * @param id 主键
    * @return PayOrderVo
    */
    PayOrderVo getVoById(Long id);
}