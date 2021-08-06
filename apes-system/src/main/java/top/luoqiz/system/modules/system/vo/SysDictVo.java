package top.luoqiz.system.modules.system.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.system.modules.system.entity.SysDictEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
@TableName("sys_dict")
@Schema(name = "SysDict对象", description = "")
public class SysDictVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long dictId;


    @Schema(description = "字典名称")
    @ExcelProperty(value = "字典名称", index = 1)
    private String name;


    @Schema(description = "描述")
    @ExcelProperty(value = "描述", index = 2)
    private String description;


    @Schema(description = "创建者")
    @ExcelProperty(value = "创建者", index = 3)
    private String createBy;


    @Schema(description = "更新者")
    @ExcelProperty(value = "更新者", index = 4)
    private String updateBy;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 5)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 6)
    private LocalDateTime updateTime;


    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    public static SysDictVo fromEntity(SysDictEntity entity) {
        if (entity == null) {
            return null;
        }
        SysDictVo vo = new SysDictVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static List<SysDictVo> fromEntity(List<SysDictEntity> entities) {
        List<SysDictVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysDictEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }


    @Override
    public String toString() {
        return "SysDict{" +
                "dictId=" + dictId +
                ", name=" + name +
                ", description=" + description +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }


}
