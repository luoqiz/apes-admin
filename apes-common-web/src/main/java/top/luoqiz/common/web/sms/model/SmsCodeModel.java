package top.luoqiz.common.web.sms.model;

import java.time.LocalDateTime;

/**
 * <p>Title: SmsCodeModel</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 验证码实体类信息
 * @date 2021/3/2 19:49
 * @since 1.0
 */
public class SmsCodeModel {

    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;
    /**
     * 业务类型
     */
    private String type;
    /**
     * 过期时间
     */
    private LocalDateTime expired;

    /**
     * 备注
     */
    private String mark;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


}
