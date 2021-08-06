package top.luoqiz.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.sms.entity.ConfigEntity;
import top.luoqiz.sms.vo.ConfigQueryCriteria;
import top.luoqiz.sms.vo.ConfigVo;

import java.util.List;

/**
 * <p>
 * 短信配置表 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
public interface ConfigService extends IService<ConfigEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < ConfigVo>>
     */
    PageResponse<List<ConfigVo>> listSearch(ConfigQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<ConfigVo>
     */
    List<ConfigVo> listSearchAll(ConfigQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return ConfigVo
     */
    ConfigVo getVoById(Long id);
}