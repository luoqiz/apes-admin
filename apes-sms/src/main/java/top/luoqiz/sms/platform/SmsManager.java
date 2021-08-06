package top.luoqiz.sms.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.sms.model.SmsCodeModel;
import top.luoqiz.common.web.sms.repository.SmsRepository;
import top.luoqiz.sms.platform.aliyun.SendMsgResponseCodeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoqiz
 */
@Service
public class SmsManager {

    private List<SmsPlatformManage> platformManages = new ArrayList<>();

    private SmsRepository smsRepository;

    @Value("${apes.sms.platform:TENG_XUN}")
    private String platform;

    public SmsRepository getSmsRepository() {
        return smsRepository;
    }

    @Autowired
    public void setSmsRepository(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Autowired
    public void setPlatformManages(List<SmsPlatformManage> platformManages) {
        this.platformManages = platformManages;
    }

    SmsPlatformManage getPlatformManage() {
        for (SmsPlatformManage platformManage : platformManages) {
            if (platformManage.support(platform)) {
                return platformManage;
            }
        }
        throw new BusinessException("not support sms platform");
    }

    public boolean sendMsg(String phone, String code) {
        SendMsgResponseCodeEnum res = getPlatformManage().sendMsg(phone, code);
        if (res.equals(SendMsgResponseCodeEnum.OK)) {
            return true;
        }
        throw new BusinessException("短信发送失败");
    }

    public boolean sendMsg(SmsCodeModel smsCodeModel) {
        SendMsgResponseCodeEnum res = getPlatformManage().sendMsg(smsCodeModel.getPhone(), smsCodeModel.getCode());
        if (res.equals(SendMsgResponseCodeEnum.OK)) {
            return true;
        }
        throw new BusinessException("短信发送失败");
    }
}
