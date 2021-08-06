package top.luoqiz.sms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;
import java.time.LocalDateTime;

/**
 * <p>
 * 短信记录表 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
public class RecordQueryCriteria extends BasePageCriteria {

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "过期时间")
    private LocalDateTime expiredTime;



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }



}