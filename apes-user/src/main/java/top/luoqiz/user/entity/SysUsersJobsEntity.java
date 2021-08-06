package top.luoqiz.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

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
public class SysUsersJobsEntity extends Model<SysUsersJobsEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    @TableId("user_id")
    private Long userId;

    @Schema(description = "岗位ID")
    @TableField("job_id")
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


    @Override
    public String toString() {
        return "SysUsersJobs{" +
               "userId=" + userId +
               ", jobId=" + jobId +
               "}";
    }

}
