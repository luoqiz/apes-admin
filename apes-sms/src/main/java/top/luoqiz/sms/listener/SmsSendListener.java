package top.luoqiz.sms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import top.luoqiz.common.web.sms.event.SmsSendEvent;
import top.luoqiz.sms.platform.SmsManager;

/**
 * 短信事件监听
 *
 * @author luoqiz
 */
@Configuration
public class SmsSendListener implements ApplicationListener<SmsSendEvent> {

    static Logger log = LoggerFactory.getLogger(SmsSendListener.class);

    private SmsManager smsManager;

    @Autowired
    public void setSmsManager(SmsManager smsManager) {
        this.smsManager = smsManager;
    }

    @Override
    public void onApplicationEvent(SmsSendEvent event) {
        log.info("向 {} 发送短信验证码 {}", event.getSmsCodeModel().getPhone(), event.getSmsCodeModel().getCode());
        log.info("发送短信实体类信息", event.getSmsCodeModel().toString());
        boolean flag = smsManager.sendMsg(event.getSmsCodeModel());
        if (!flag) {
            log.error("向 {} 发送短信验证码 {} 失败", event.getSmsCodeModel().getPhone(), event.getSmsCodeModel().getCode());
        }
    }
}
