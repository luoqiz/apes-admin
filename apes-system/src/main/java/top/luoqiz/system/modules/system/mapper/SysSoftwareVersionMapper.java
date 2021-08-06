package top.luoqiz.system.modules.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.luoqiz.system.modules.system.entity.SysSoftwareVersionEntity;

import java.util.List;

/**
 * <p>
 * 软件版本 Mapper 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-05-21
 */
public interface SysSoftwareVersionMapper extends BaseMapper<SysSoftwareVersionEntity> {

    /**
     * 分页查询数据
     *
     * @param wrapper mybatis-plus 构造条件
     * @param offset  偏移量
     * @param size    数据量
     * @return List<SysSoftwareVersionEntity>
     */
    List<SysSoftwareVersionEntity> selectListPage(@Param("ew") Wrapper wrapper, @Param("offset") int offset, @Param("size") Integer size);

}
