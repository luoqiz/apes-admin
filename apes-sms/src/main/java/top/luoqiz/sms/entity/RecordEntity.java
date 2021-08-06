package top.luoqiz.sms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 短信记录表
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
@TableName("sms_record")
@Schema(name = "Record对象", description = "短信记录表")
public class RecordEntity extends Model<RecordEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("id")
    private Long id;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "验证码")
    private String code;

    @Schema(description = "备注")
    private String mark;

    @Schema(description = "过期时间")
    private LocalDateTime expiredTime;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
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
        return "Record{" +
               "id=" + id +
               ", phone=" + phone +
               ", code=" + code +
               ", mark=" + mark +
               ", expiredTime=" + expiredTime +
               ", createTime=" + createTime +
               ", updateTime=" + updateTime +
               "}";
    }

}
