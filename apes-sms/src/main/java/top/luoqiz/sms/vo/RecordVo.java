package top.luoqiz.sms.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.sms.entity.RecordEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
public class RecordVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long id;


    @Schema(description = "手机号")
    @ExcelProperty(value = "手机号", index = 1)
    private String phone;


    @Schema(description = "验证码")
    @ExcelProperty(value = "验证码", index = 2)
    private String code;


    @Schema(description = "备注")
    @ExcelProperty(value = "备注", index = 3)
    private String mark;


    @Schema(description = "过期时间")
    @ExcelProperty(value = "过期时间", index = 4)
    private LocalDateTime expiredTime;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 5)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 6)
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



    public static RecordVo fromEntity(RecordEntity entity) {
        if (entity == null) {
            return null;
        }
        RecordVo vo = new RecordVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static List<RecordVo> fromEntity(List<RecordEntity> entities) {
        List<RecordVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (RecordEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
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
