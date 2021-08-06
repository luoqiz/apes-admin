package top.luoqiz.common.web.sms.repository;

import top.luoqiz.common.web.sms.model.SmsCodeModel;

import java.util.Optional;

/**
 * 发送验证的记录保存
 *
 * @author luoqiz
 */
public interface SmsRepository {

    /**
     * 生成验证码信息
     *
     * @param phone       手机号
     * @param expiredTime 过期时长，以秒为单位
     * @return SmsCodeModel
     */
    SmsCodeModel createSmsCode(String phone, Integer expiredTime);

    /**
     * 生成验证码信息
     *
     * @param phone       手机号
     * @param type        指定业务类型
     * @param expiredTime 过期时长，以秒为单位
     * @return SmsCodeModel
     */
    SmsCodeModel createSmsCode(String phone, String type, Integer expiredTime);

    /**
     * 生成验证码信息
     *
     * @param phone       手机号
     * @param type        指定业务类型
     * @param length      生成的验证码长度
     * @param expiredTime 过期时长，以秒为单位
     * @return SmsCodeModel
     */
    SmsCodeModel createSmsCode(String phone, String type, Integer length, Integer expiredTime);

    /**
     * 保存 短信验证码 信息
     *
     * @param smsCodeModel 短信验证码对象
     * @return true or false
     */
    boolean save(SmsCodeModel smsCodeModel);

    /**
     * 获取 短信验证码 对象
     *
     * @param phone 手机号
     * @return Optional<SmsCodeModel>
     */
    Optional<SmsCodeModel> getSmsCodeInfo(String phone);

    /**
     * 验证短信验证码有效性
     *
     * @param phone 手机号
     * @param type  指定业务类型
     * @param code  验证码
     * @return true or false
     */
    boolean checkRenewal(String phone, String type, String code);

    /**
     * 删除信息
     *
     * @param phone 手机号
     * @return true or false
     */
    boolean remove(String phone);

    /**
     * 验证手机验证码是否发送或者过期
     *
     * @param phone 手机号
     * @param type  业务类型
     * @return
     */
    boolean checkRenewal(String phone, String type);
}
