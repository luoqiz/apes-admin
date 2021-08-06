package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysUsersJobsEntity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-19
 */
@TableName("sys_users_jobs")
@Schema(name = "SysUsersJobs对象", description = "")
public class SysUsersJobsVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "用户ID")
    @ExcelProperty(value = "用户ID", index = 0)
    private Long userId;


    @Schema(description = "岗位ID")
    @ExcelProperty(value = "岗位ID", index = 1)
    private Long jobId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }


    public static SysUsersJobsVo fromEntity(SysUsersJobsEntity entity) {
        if (entity == null) {
            return null;
        }
        SysUsersJobsVo vo = new SysUsersJobsVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<SysUsersJobsVo> fromEntity(List<SysUsersJobsEntity> entities) {
        LinkedList<SysUsersJobsVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysUsersJobsEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
    }


    @Override
    public String toString() {
        return "SysUsersJobs{" +
                "userId=" + userId +
                ", jobId=" + jobId +
                "}";
    }


}
