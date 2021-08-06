package top.luoqiz.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.luoqiz.user.entity.SysDeptEntity;

import java.util.List;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {

    /**
     * 分页查询数据
     *
     * @param wrapper mybatis-plus 构造条件
     * @param offset  偏移量
     * @param size    数据量
     * @return List<SysDeptEntity>
     */
    List<SysDeptEntity> selectListPage(@Param("ew") Wrapper wrapper, @Param("offset") int offset, @Param("size") Integer size);
}
