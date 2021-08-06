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
 * 部门
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@TableName("sys_dept")
@Schema(name = "SysDept对象", description = "部门")
public class SysDeptEntity extends Model<SysDeptEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("dept_id")
    private Long deptId;

    @Schema(description = "上级部门")
    private Long pid;

    @Schema(description = "子部门数目")
    private Integer subCount;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "排序")
    private Integer deptSort;

    @Schema(description = "状态")
    private Boolean enabled;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeptSort() {
        return deptSort;
    }

    public void setDeptSort(Integer deptSort) {
        this.deptSort = deptSort;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
        return "SysDept{" +
               "deptId=" + deptId +
               ", pid=" + pid +
               ", subCount=" + subCount +
               ", name=" + name +
               ", deptSort=" + deptSort +
               ", enabled=" + enabled +
               ", createBy=" + createBy +
               ", updateBy=" + updateBy +
               ", createTime=" + createTime +
               ", updateTime=" + updateTime +
               "}";
    }

}
