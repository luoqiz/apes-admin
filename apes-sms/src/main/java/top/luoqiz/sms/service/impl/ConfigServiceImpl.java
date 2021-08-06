package top.luoqiz.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.sms.entity.ConfigEntity;
import top.luoqiz.sms.mapper.ConfigMapper;
import top.luoqiz.sms.service.ConfigService;
import top.luoqiz.sms.vo.ConfigQueryCriteria;
import top.luoqiz.sms.vo.ConfigVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 短信配置表 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, ConfigEntity> implements ConfigService {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < ConfigVo>>
     */
    @Override
    public PageResponse<List<ConfigVo>> listSearch(ConfigQueryCriteria criteria) {
        LambdaQueryWrapper<ConfigEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total < 1 || total <= (criteria.getPage() - 1) * criteria.getSize()) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        return PageResponse.build(ConfigVo.fromEntity(this.list(queryWrapper)), total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria
     * @return List<ConfigVo>
     */
    @Override
    public List<ConfigVo> listSearchAll(ConfigQueryCriteria criteria) {
        LambdaQueryWrapper<ConfigEntity> queryWrapper = buildQuery(criteria);
        return ConfigVo.fromEntity(this.list(queryWrapper));
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return ConfigVo
     */
    @Override
    public ConfigVo getVoById(Long id) {
        return ConfigVo.fromEntity(getById(id));
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<ConfigEntity>
     */
    private LambdaQueryWrapper<ConfigEntity> buildQuery(ConfigQueryCriteria criteria) {

        LambdaQueryWrapper<ConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(criteria.getPlatformType()).ifPresent(value -> queryWrapper.eq(ConfigEntity::getPlatformType, value));
        Optional.ofNullable(criteria.getSignName()).ifPresent(value -> queryWrapper.like(ConfigEntity::getSignName, value));
        Optional.ofNullable(criteria.getTemplateCode()).ifPresent(value -> queryWrapper.like(ConfigEntity::getTemplateCode, value));
        return queryWrapper;
    }
}