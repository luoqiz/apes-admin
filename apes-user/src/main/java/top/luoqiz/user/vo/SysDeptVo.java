package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysDeptEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
public class SysDeptVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long deptId;


    @Schema(description = "上级部门")
    @ExcelProperty(value = "上级部门", index = 1)
    private Long pid;


    @Schema(description = "子部门数目")
    @ExcelProperty(value = "子部门数目", index = 2)
    private Integer subCount;


    @Schema(description = "名称")
    @ExcelProperty(value = "名称", index = 3)
    private String name;


    @Schema(description = "排序")
    @ExcelProperty(value = "排序", index = 4)
    private Integer deptSort;


    @Schema(description = "状态")
    @ExcelProperty(value = "状态", index = 5)
    private Boolean enabled;


    @Schema(description = "创建者")
    @ExcelProperty(value = "创建者", index = 6)
    private String createBy;


    @Schema(description = "更新者")
    @ExcelProperty(value = "更新者", index = 7)
    private String updateBy;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 8)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 9)
    private LocalDateTime updateTime;

    @Schema(description = "子级部门")
    @ExcelProperty(value = "子级部门", index = 10)
    private List<SysDeptVo> children;

    public List<SysDeptVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysDeptVo> children) {
        this.children = children;
    }


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


    public static SysDeptVo fromEntity(SysDeptEntity entity) {
        if (entity == null) {
            return null;
        }
        SysDeptVo vo = new SysDeptVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<SysDeptVo> fromEntity(List<SysDeptEntity> entities) {
        LinkedList<SysDeptVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysDeptEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
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
