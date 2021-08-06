package top.luoqiz.sms.platform;

import top.luoqiz.sms.platform.aliyun.SendMsgResponseCodeEnum;

/**
 * @author luoqiz
 */
public interface SmsPlatformManage {

    boolean support(String platform);

    /**
     * 发送包含手机号和用户名的短信
     *
     * @param phone    手机号
     * @param username 用户名
     * @param code     验证码
     * @return String
     */
    String sendMsg(String phone, String username, String code);

    /**
     * 发送包含手机号的短信
     *
     * @param phone 手机号
     * @param code  验证码
     * @return SendMsgResponseCodeEnum
     */
    SendMsgResponseCodeEnum sendMsg(String phone, String code);
}
