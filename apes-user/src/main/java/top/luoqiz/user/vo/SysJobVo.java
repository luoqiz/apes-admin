package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysJobEntity;

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
 * @since 2021-04-04
 */
@TableName("sys_job")
@Schema(name = "SysJob对象", description = "")
public class SysJobVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long jobId;


    @Schema(description = "岗位名称")
    @ExcelProperty(value = "岗位名称", index = 1)
    private String name;


    @Schema(description = "岗位状态")
    @ExcelProperty(value = "岗位状态", index = 2)
    private Boolean enabled;


    @Schema(description = "排序")
    @ExcelProperty(value = "排序", index = 3)
    private Integer jobSort;


    @Schema(description = "创建者")
    @ExcelProperty(value = "创建者", index = 4)
    private String createBy;


    @Schema(description = "更新者")
    @ExcelProperty(value = "更新者", index = 5)
    private String updateBy;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 6)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 7)
    private LocalDateTime updateTime;


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getJobSort() {
        return jobSort;
    }

    public void setJobSort(Integer jobSort) {
        this.jobSort = jobSort;
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


    public static SysJobVo fromEntity(SysJobEntity entity) {
        if (entity == null) {
            return null;
        }
        SysJobVo vo = new SysJobVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static List<SysJobVo> fromEntity(List<SysJobEntity> entities) {
        List<SysJobVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysJobEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }


    @Override
    public String toString() {
        return "SysJob{" +
                "jobId=" + jobId +
                ", name=" + name +
                ", enabled=" + enabled +
                ", jobSort=" + jobSort +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }


}
