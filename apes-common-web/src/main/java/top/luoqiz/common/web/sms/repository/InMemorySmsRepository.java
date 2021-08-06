package top.luoqiz.common.web.sms.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import top.luoqiz.common.web.sms.model.SmsCodeModel;
import top.luoqiz.common.web.utils.SmsCodeUtil;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 内存中保存 Sms 信息
 *
 * @author EDZ
 */
@ConditionalOnMissingBean(name = "smsRepository")
@Component
public class InMemorySmsRepository implements SmsRepository {
    static Logger log = LoggerFactory.getLogger(InMemorySmsRepository.class);
    public static CopyOnWriteArrayList<SmsCodeModel> codeModels = new CopyOnWriteArrayList<>();
    private static boolean inUse = false;

    /**
     * 创建短信验证码对象
     *
     * @return SmsCodeModel 短信验证码对象
     */
    @Override
    public SmsCodeModel createSmsCode(String phone, Integer expiredTime) {
        return createSmsCode(phone, "", 6, expiredTime);
    }

    @Override
    public SmsCodeModel createSmsCode(String phone, String type, Integer expiredTime) {
        return createSmsCode(phone, type, 6, expiredTime);
    }

    /**
     * 创建短信验证码对象
     *
     * @return SmsCodeModel 短信验证码对象
     */
    @Override
    public SmsCodeModel createSmsCode(String phone, String type, Integer length, Integer expiredTime) {
        Optional<SmsCodeModel> smsCodeModel = codeModels.stream().parallel()
                .filter(smsCode -> smsCode.getPhone().equals(phone)).findFirst();
        if (smsCodeModel.isPresent()) {
            if (smsCodeModel.get().getExpired().isBefore(LocalDateTime.now())) {
                codeModels.remove(smsCodeModel.get());
            } else {
                return smsCodeModel.get();
            }
        }
        SmsCodeModel smsCode = new SmsCodeModel();
        smsCode.setPhone(phone);
        smsCode.setType(type);
        if (length == null || length < 1) {
            length = 6;
        }
        smsCode.setCode(SmsCodeUtil.randomStr(length));
        if (expiredTime != null && expiredTime > 0) {
            smsCode.setExpired(LocalDateTime.now().plusSeconds(expiredTime));
        } else {
            smsCode.setExpired(LocalDateTime.now().plusMinutes(5));
        }
        log.debug("验证码生成： {}", smsCode);
        return smsCode;
    }

    @Override
    public boolean save(SmsCodeModel smsCodeModel) {
        clearSmsCode();
        codeModels.add(smsCodeModel);
        return true;
    }

    @Override
    public Optional<SmsCodeModel> getSmsCodeInfo(String phone) {
        Optional<SmsCodeModel> smsCodeModel = codeModels.stream().parallel()
                .filter(smsCode -> smsCode.getPhone().equals(phone)).findFirst();
        if (smsCodeModel.isPresent()) {
            if (smsCodeModel.get().getExpired().isBefore(LocalDateTime.now())) {
                codeModels.remove(smsCodeModel.get());
                return Optional.empty();
            }
        }
        return smsCodeModel;
    }

    /**
     * 验证短信验证码有效性
     *
     * @param phone 手机号
     * @param type  业务类型
     * @param code  验证码
     */
    @Override
    public boolean checkRenewal(String phone, String type, String code) {
        Optional<SmsCodeModel> smsCodeModel = codeModels.stream().parallel()
                .filter(smsCode -> smsCode.getPhone().equals(phone))
                .filter(smsCode -> smsCode.getType().equals(type))
                .filter(smsCode -> smsCode.getCode().equals(code))
                .findFirst();
        if (smsCodeModel.isPresent()) {
            if (smsCodeModel.get().getExpired().isBefore(LocalDateTime.now())) {
                codeModels.remove(smsCodeModel.get());
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String phone) {
        Iterator<SmsCodeModel> iter = codeModels.iterator();
        while (iter.hasNext()) {
            SmsCodeModel model = iter.next();
            if (model.getPhone().equals(phone)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkRenewal(String phone, String type) {
        Optional<SmsCodeModel> smsCodeModel = codeModels.stream().parallel()
                .filter(smsCode -> smsCode.getPhone().equals(phone))
                .filter(smsCode -> smsCode.getType().equals(type))
                .findFirst();
        if (smsCodeModel.isPresent()) {
            if (smsCodeModel.get().getExpired().isBefore(LocalDateTime.now())) {
                codeModels.remove(smsCodeModel.get());
                return false;
            }
            return true;
        }
        return false;
    }


    /**
     * 删除过期的验证码
     */
    private static void clearSmsCode() {
        if (!inUse) {
            new Timer("clear sms code ").schedule(new TimerTask() {
                @Override
                public void run() {
                    codeModels.forEach(codeModel -> {
                        if (codeModel.getExpired().isBefore(LocalDateTime.now())) {
                            codeModels.remove(codeModel);
                        }
                    });
                    log.info(Thread.currentThread().getName() + "Clear expired code thread running...... ");
                }
            }, 1000, 30 * 60 * 1000);
            inUse = true;
        }
    }

}
