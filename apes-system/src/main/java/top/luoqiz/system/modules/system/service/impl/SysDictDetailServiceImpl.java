package top.luoqiz.system.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.system.modules.system.entity.SysDictDetailEntity;
import top.luoqiz.system.modules.system.entity.SysDictEntity;
import top.luoqiz.system.modules.system.mapper.SysDictDetailMapper;
import top.luoqiz.system.modules.system.queryCriteria.SysDictDetailQueryCriteria;
import top.luoqiz.system.modules.system.service.SysDictDetailService;
import top.luoqiz.system.modules.system.service.SysDictService;
import top.luoqiz.system.modules.system.vo.SysDictDetailVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务 实现类
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
@Service
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetailEntity> implements SysDictDetailService {

    private SysDictService sysDictService;

    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysDictDetailVo>>
     */
    @Override
    public PageResponse<List<SysDictDetailVo>> listSearch(SysDictDetailQueryCriteria criteria) {
        LambdaQueryWrapper<SysDictDetailEntity> queryWrapper = buildQuery(criteria);
        int total = this.count(queryWrapper);
        if (total <= 0) {
            return PageResponse.build(new ArrayList<>(), total, criteria.getPage(), criteria.getSize());
        }
        queryWrapper.last(String.format("limit %d,%d", (criteria.getPage() - 1) * criteria.getSize(), criteria.getSize()));
        List<SysDictDetailVo> dictDetailVos = SysDictDetailVo.fromEntity(this.list(queryWrapper));
        List<SysDictEntity> dictList = sysDictService.listByIds(dictDetailVos.stream().map(SysDictDetailVo::getDictId).collect(Collectors.toList()));
        List<SysDictDetailVo> res = dictDetailVos.stream()
                .flatMap(x -> dictList.stream()
                        .filter(y -> x.getDictId().equals(y.getDictId()))
                        .map(z -> {
                            x.setDictName(z.getName());
                            return x;
                        })
                ).collect(Collectors.toList());

        return PageResponse.build(res, total, criteria.getPage(), criteria.getSize());
    }

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件实体类
     * @return List<SysDictDetailVo>
     */
    @Override
    public List<SysDictDetailVo> listSearchAll(SysDictDetailQueryCriteria criteria) {
        LambdaQueryWrapper<SysDictDetailEntity> queryWrapper = buildQuery(criteria);
        List<SysDictDetailVo> dictDetailVos = SysDictDetailVo.fromEntity(this.list(queryWrapper));
        if (dictDetailVos.isEmpty()) {
            return new ArrayList<>();
        }
        List<SysDictEntity> dictList = sysDictService.listByIds(dictDetailVos.stream().map(SysDictDetailVo::getDictId).collect(Collectors.toList()));
        return dictDetailVos.stream()
                .flatMap(x -> dictList.stream()
                        .filter(y -> x.getDictId().equals(y.getDictId()))
                        .map(z -> {
                            x.setDictName(z.getName());
                            return x;
                        })
                ).collect(Collectors.toList());
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return SysDictDetailVo
     */
    @Override
    public SysDictDetailVo getVoById(Long id) {
        SysDictDetailVo sysdictDetails = SysDictDetailVo.fromEntity(getById(id));
        SysDictEntity dictCategory = sysDictService.getById(sysdictDetails.getDictId());
        sysdictDetails.setDictName(dictCategory.getName());
        return sysdictDetails;
    }

    /**
     * 构建查询实体类
     *
     * @param criteria 查询条件
     * @return LambdaQueryWrapper<SysDictDetailEntity>
     */
    private LambdaQueryWrapper<SysDictDetailEntity> buildQuery(SysDictDetailQueryCriteria criteria) {
        LambdaQueryWrapper<SysDictDetailEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(SysDictDetailEntity::getDictSort);

        if (criteria.getDictId() != null) {
            queryWrapper.eq(SysDictDetailEntity::getDictId, criteria.getDictId());
            return queryWrapper;
        }

        Optional.ofNullable(criteria.getDictKey()).ifPresent(value -> {
            SysDictEntity dict = sysDictService.getOne(new LambdaQueryWrapper<SysDictEntity>().eq(SysDictEntity::getName, value));
            if (dict != null) {
                queryWrapper.in(SysDictDetailEntity::getDictId, dict.getDictId());
            }
        });
        return queryWrapper;
    }

    @Autowired
    public void setSysDictService(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

}