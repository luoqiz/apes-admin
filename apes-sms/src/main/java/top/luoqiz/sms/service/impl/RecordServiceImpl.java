package top.luoqiz.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.sms.entity.RecordEntity;
import top.luoqiz.sms.mapper.RecordMapper;
import top.luoqiz.sms.service.RecordService;
import top.luoqiz.sms.vo.RecordQueryCriteria;
import top.luoqiz.sms.vo.RecordVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 短信记录表 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, RecordEntity> implements RecordService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < RecordVo>>
     */
    @Override
    public PageResponse<List<RecordVo>> listSearch(RecordQueryCriteria criteria) {
        LambdaQueryWrapper<RecordEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(RecordVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<RecordVo>
     */
    @Override
    public List<RecordVo> listSearchAll(RecordQueryCriteria criteria) {
        LambdaQueryWrapper<RecordEntity> queryWrapper = buildQuery(criteria);
        return RecordVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return RecordVo
     */
    @Override
    public RecordVo getVoById(Long id) {
        return RecordVo.fromEntity(getById(id));
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<RecordEntity>
     */
    private LambdaQueryWrapper<RecordEntity> buildQuery(RecordQueryCriteria criteria) {

        LambdaQueryWrapper<RecordEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getPhone()).ifPresent(value -> queryWrapper.eq(RecordEntity::getPhone, value));
        Optional.ofNullable(criteria.getExpiredTime()).ifPresent(value -> queryWrapper.gt(RecordEntity::getExpiredTime, value));
        return queryWrapper;
    }
}