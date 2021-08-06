package top.luoqiz.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.sms.model.SmsCodeModel;
import top.luoqiz.common.web.sms.repository.SmsRepository;
import top.luoqiz.sms.platform.SmsManager;
import top.luoqiz.sms.service.SmsManageService;

/**
 * @author luoqiz
 */

@Service
public class SmsManageServiceImpl implements SmsManageService {

    private SmsRepository smsRepository;
    private SmsManager smsManager;

    @Autowired
    public void setSmsRepository(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Autowired
    public void setSmsManager(SmsManager smsManager) {
        this.smsManager = smsManager;
    }

    @Override
    public boolean sendSmsMsg(String phone, String username) {
        SmsCodeModel smsCode = smsRepository.createSmsCode(phone, 300);
        // 发送验证码
        smsManager.sendMsg(phone, smsCode.getCode());
        // 发送验证码成功后保存
        smsRepository.save(smsCode);
        return true;
    }
}
