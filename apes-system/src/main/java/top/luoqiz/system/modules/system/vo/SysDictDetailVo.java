package top.luoqiz.system.modules.system.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.system.modules.system.entity.SysDictDetailEntity;

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
@TableName("sys_dict_detail")
@Schema(name = "SysDictDetail对象", description = "")
public class SysDictDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long detailId;


    @Schema(description = "所属字典")
    @ExcelProperty(value = "所属字典", index = 1)
    private String dictName;

    @Schema(description = "字典id")
    private Long dictId;

    @Schema(description = "字典标签")
    @ExcelProperty(value = "字典标签", index = 2)
    private String label;

    @Schema(description = "字典值")
    @ExcelProperty(value = "字典值", index = 3)
    private String value;


    @Schema(description = "排序")
    @ExcelProperty(value = "排序", index = 4)
    private Integer dictSort;


    @Schema(description = "创建者")
    @ExcelProperty(value = "创建者", index = 5)
    private String createBy;


    @Schema(description = "更新者")
    @ExcelProperty(value = "更新者", index = 6)
    private String updateBy;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 7)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 8)
    private LocalDateTime updateTime;


    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
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


    public static SysDictDetailVo fromEntity(SysDictDetailEntity entity) {
        if (entity == null) {
            return null;
        }
        SysDictDetailVo vo = new SysDictDetailVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static List<SysDictDetailVo> fromEntity(List<SysDictDetailEntity> entities) {
        List<SysDictDetailVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysDictDetailEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }


    @Override
    public String toString() {
        return "SysDictDetail{" +
                "detailId=" + detailId +
                ", dictId=" + dictId +
                ", dictName=" + dictName +
                ", label=" + label +
                ", value=" + value +
                ", dictSort=" + dictSort +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }


    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
