package top.luoqiz.system.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoqiz.common.web.response.vo.PageResponse;
import top.luoqiz.system.modules.system.entity.SysSoftwareVersionEntity;
import top.luoqiz.system.modules.system.queryCriteria.SysSoftwareVersionQueryCriteria;
import top.luoqiz.system.modules.system.vo.SysSoftwareVersionVo;

import java.util.List;

/**
 * <p>
 * 软件版本 服务 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-05-21
 */
public interface SysSoftwareVersionService extends IService<SysSoftwareVersionEntity> {
    /**
     * 条件查询分页列表
     *
     * @param criteria 查询条件构建类
     * @return PageResponse<List < SysSoftwareVersionVo>>
     */
    PageResponse<List<SysSoftwareVersionVo>> listSearch(SysSoftwareVersionQueryCriteria criteria);

    /**
     * 条件查询全部列表
     *
     * @param criteria 查询条件构建类
     * @return List<SysSoftwareVersionVo>
     */
    List<SysSoftwareVersionVo> listSearchAll(SysSoftwareVersionQueryCriteria criteria);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return SysSoftwareVersionVo
     */
    SysSoftwareVersionVo getVoById(Long id);

    /**
     * 获取最新版本信息
     *
     * @param type 软件类型
     * @return SysSoftwareVersionVo
     */
    SysSoftwareVersionVo latest(String type);
}