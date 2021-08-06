package top.luoqiz.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

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
public class SysJobEntity extends Model<SysJobEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("job_id")
    private Long jobId;

    @Schema(description = "岗位名称")
    private String name;

    @Schema(description = "岗位状态")
    private Boolean enabled;

    @Schema(description = "排序")
    private Integer jobSort;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
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
