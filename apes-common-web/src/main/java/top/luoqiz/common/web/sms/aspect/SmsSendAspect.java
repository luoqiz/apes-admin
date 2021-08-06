package top.luoqiz.common.web.sms.aspect;

import io.undertow.util.BadRequestException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.common.web.sms.annotation.SmsSend;
import top.luoqiz.common.web.sms.event.SmsSendEvent;
import top.luoqiz.common.web.sms.model.SmsCodeModel;
import top.luoqiz.common.web.sms.repository.SmsRepository;
import top.luoqiz.common.web.utils.SmsCodeUtil;

import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * @author luoqiz
 */
//@Slf4j
@Aspect
@Configuration
public class SmsSendAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());
    // 注入事件发布者
    private ApplicationEventPublisher applicationEventPublisher;

    private SmsRepository smsRepository;

    @Autowired
    public void setSmsRepository(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Pointcut("@annotation(top.luoqiz.common.web.sms.annotation.SmsSend)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws BadRequestException {
        try {
            //1.获取到所有的参数值的数组
            Object[] args = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            //2.获取到方法的所有参数名称的字符串数组
            String[] parameterNames = methodSignature.getParameterNames();
            Method method = methodSignature.getMethod();
            SmsSend smsSend = method.getAnnotation(SmsSend.class);
            String phone = smsSend.phone();
            String type = smsSend.type();
            int second = smsSend.second();

            LinkedList<SmsCodeUtil.Param> params = SmsCodeUtil.matcher(phone);

            log.info("---------------参数列表开始-------------------------");
            for (int i = 0, len = parameterNames.length; i < len; i++) {
                log.info("参数名：" + parameterNames[i] + " = " + args[i]);
                for (SmsCodeUtil.Param param : params) {
                    if (param.name.equals(parameterNames[i])) {
                        param.value = args[i].toString();
                    }
                }
            }
            log.info("---------------参数列表结束-------------------------");

            if (params.size() > 0) {
                for (SmsCodeUtil.Param param : params) {
                    phone = phone.replaceFirst("\\{" + param.name + "}", param.value);
                }
            }

            if (smsRepository.checkRenewal(phone, type)) {
                log.info("业务类型为 {} 手机号为 {} 的短信验证码尚在有效期内", type, phone);
                return joinPoint.proceed();
            }
            SmsCodeModel smsCodeModel = smsRepository.createSmsCode(phone, type, 6, second);
            smsRepository.save(smsCodeModel);
            applicationEventPublisher.publishEvent(new SmsSendEvent(this, smsCodeModel));
            return joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new BusinessException(ResponseCode.REGISTER_SMS_ERROR);
    }


}


