package top.luoqiz.common.web.sms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.common.web.sms.annotation.SmsValidate;
import top.luoqiz.common.web.sms.repository.SmsRepository;
import top.luoqiz.common.web.utils.SmsCodeUtil;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author luoqiz
 */
//@Slf4j
@Aspect
@Component
public class SmsValidateAspect {

    Logger log = LoggerFactory.getLogger(SmsValidateAspect.class);

    private SmsRepository smsRepository;

    @Autowired
    public void setSmsRepository(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }


    @Pointcut("@annotation(top.luoqiz.common.web.sms.annotation.SmsValidate)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.获取到所有的参数值的数组
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature signatureMethod = (MethodSignature) signature;
        //2.获取到方法的所有参数名称的字符串数组
        String[] parameterNames = signatureMethod.getParameterNames();
        Method method = signatureMethod.getMethod();
        SmsValidate smsValidate = method.getAnnotation(SmsValidate.class);
        String phone = smsValidate.phone();
        String type = smsValidate.type();
        String code = smsValidate.code();

        LinkedList<SmsCodeUtil.Param> phoneParams = SmsCodeUtil.matcher(phone);
        LinkedList<SmsCodeUtil.Param> codeParams = SmsCodeUtil.matcher(code);

        log.info("---------------参数列表开始-------------------------");
        // 非get请求
        if (parameterNames.length == 1 && args[0] instanceof Map) {
            Map<?, ?> params = (Map<?, ?>) args[0];
            Iterator<? extends Map.Entry<?, ?>> iterable = params.entrySet().iterator();
            while (iterable.hasNext()) {
                Map.Entry<?, ?> entry = iterable.next();
                for (SmsCodeUtil.Param param : phoneParams) {
                    if (param.name.equals(entry.getKey())) {
                        param.value = entry.getValue().toString();
                    }
                }
                for (SmsCodeUtil.Param param : codeParams) {
                    if (param.name.equals(entry.getKey())) {
                        param.value = entry.getValue().toString();
                    }
                }
                log.info("参数名：" + entry.getKey() + " = " + entry.getValue());
            }
        } else {
            for (int i = 0, len = parameterNames.length; i < len; i++) {
                log.info("参数名：" + parameterNames[i] + " = " + args[i]);
                for (SmsCodeUtil.Param param : phoneParams) {
                    if (param.name.equals(parameterNames[i])) {
                        param.value = args[i].toString();
                    }
                }
                for (SmsCodeUtil.Param param : codeParams) {
                    if (param.name.equals(parameterNames[i])) {
                        param.value = args[i].toString();
                    }
                }
            }
        }
        log.info("---------------参数列表结束-------------------------");

        if (phoneParams.size() > 0) {
            for (SmsCodeUtil.Param param : phoneParams) {
                phone = phone.replaceFirst("\\{" + param.name + "}", param.value);
            }
        }

        if (codeParams.size() > 0) {
            for (SmsCodeUtil.Param param : codeParams) {
                code = code.replaceFirst("\\{" + param.name + "}", param.value);
            }
        }

        if (smsRepository.checkRenewal(phone, type, code)) {
            return joinPoint.proceed();
        }
        throw new BusinessException(ResponseCode.REGISTER_VALIDATE_CODE_ERROR);
    }
}
