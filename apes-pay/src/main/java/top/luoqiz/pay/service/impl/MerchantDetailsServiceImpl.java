package top.luoqiz.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.pay.entity.MerchantDetailsEntity;
import top.luoqiz.pay.mapper.MerchantDetailsMapper;
import top.luoqiz.pay.vo.MerchantDetailsQueryCriteria;
import top.luoqiz.pay.service.MerchantDetailsService;
import top.luoqiz.pay.vo.MerchantDetailsVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 支付应用信息 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-08
 */
@Service
public class MerchantDetailsServiceImpl extends ServiceImpl<MerchantDetailsMapper, MerchantDetailsEntity> implements MerchantDetailsService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < MerchantDetailsVo>>
     */
    @Override
    public PageResponse<List<MerchantDetailsVo>> listSearch(MerchantDetailsQueryCriteria criteria) {
        LambdaQueryWrapper<MerchantDetailsEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(MerchantDetailsVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<MerchantDetailsVo>
     */
    @Override
    public List<MerchantDetailsVo> listSearchAll(MerchantDetailsQueryCriteria criteria) {
        LambdaQueryWrapper<MerchantDetailsEntity> queryWrapper = buildQuery(criteria);
        return MerchantDetailsVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return MerchantDetailsVo
     */
    @Override
    public MerchantDetailsVo getVoById(String id) {
        return MerchantDetailsVo.fromEntity(getById(id));
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<MerchantDetailsEntity>
     */
    private LambdaQueryWrapper<MerchantDetailsEntity> buildQuery(MerchantDetailsQueryCriteria criteria) {

        LambdaQueryWrapper<MerchantDetailsEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getDetailsId()).ifPresent(value -> queryWrapper.eq(MerchantDetailsEntity::getDetailsId, value));
        Optional.ofNullable(criteria.getPayType()).ifPresent(value -> queryWrapper.eq(MerchantDetailsEntity::getPayType, value));
        Optional.ofNullable(criteria.getAppid()).ifPresent(value -> queryWrapper.like(MerchantDetailsEntity::getAppid, value));
        Optional.ofNullable(criteria.getMchId()).ifPresent(value -> queryWrapper.like(MerchantDetailsEntity::getMchId, value));
        Optional.ofNullable(criteria.getSubAppId()).ifPresent(value -> queryWrapper.like(MerchantDetailsEntity::getSubAppId, value));
        Optional.ofNullable(criteria.getSubMchId()).ifPresent(value -> queryWrapper.like(MerchantDetailsEntity::getSubMchId, value));
        Optional.ofNullable(criteria.getIsTest()).ifPresent(value -> queryWrapper.eq(MerchantDetailsEntity::getIsTest, value));
        return queryWrapper;
    }
}