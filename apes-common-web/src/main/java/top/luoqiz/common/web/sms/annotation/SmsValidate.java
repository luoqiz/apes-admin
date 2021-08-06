package top.luoqiz.common.web.sms.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luoqiz
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmsValidate {
    // 手机号
    String phone();

    // 业务类型
    String type();

    // 验证码
    String code();
}
