package top.luoqiz.common.web.sms.event;

import org.springframework.context.ApplicationEvent;
import top.luoqiz.common.web.sms.model.SmsCodeModel;

/**
 * @author luoqiz
 */
public class SmsSendEvent extends ApplicationEvent {
    private SmsCodeModel smsCodeModel;

    public SmsCodeModel getSmsCodeModel() {
        return smsCodeModel;
    }

    public void setSmsCodeModel(SmsCodeModel smsCodeModel) {
        this.smsCodeModel = smsCodeModel;
    }


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SmsSendEvent(Object source) {
        super(source);
    }

    public SmsSendEvent(Object source, SmsCodeModel smsCodeModel) {
        super(source);
        this.smsCodeModel = smsCodeModel;
    }
}
