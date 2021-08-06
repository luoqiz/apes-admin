package top.luoqiz.sms.service;

/**
 * @author luoqiz
 */
public interface SmsManageService {

    boolean sendSmsMsg(String phone, String username);
}
