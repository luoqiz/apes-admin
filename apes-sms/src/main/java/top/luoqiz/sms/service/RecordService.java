package top.luoqiz.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.sms.entity.RecordEntity;
import top.luoqiz.sms.vo.RecordQueryCriteria;
import top.luoqiz.sms.vo.RecordVo;

import java.util.List;

/**
 * <p>
 * 短信记录表 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
public interface RecordService extends IService<RecordEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < RecordVo>>
     */
    PageResponse<List<RecordVo>> listSearch(RecordQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<RecordVo>
     */
    List<RecordVo> listSearchAll(RecordQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return RecordVo
     */
    RecordVo getVoById(Long id);
}