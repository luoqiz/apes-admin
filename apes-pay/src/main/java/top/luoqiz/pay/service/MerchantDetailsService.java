package top.luoqiz.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.pay.entity.MerchantDetailsEntity;
import top.luoqiz.pay.vo.MerchantDetailsQueryCriteria;
import top.luoqiz.pay.vo.MerchantDetailsVo;

import java.util.List;

/**
 * <p>
 * 支付应用信息 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-08
 */
public interface MerchantDetailsService extends IService<MerchantDetailsEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < MerchantDetailsVo>>
     */
    PageResponse<List<MerchantDetailsVo>> listSearch(MerchantDetailsQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<MerchantDetailsVo>
     */
    List<MerchantDetailsVo> listSearchAll(MerchantDetailsQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return MerchantDetailsVo
     */
    MerchantDetailsVo getVoById(String id);
}