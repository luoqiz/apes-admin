package top.luoqiz.common.web.validator;

import cn.hutool.core.util.IdcardUtil;
import top.luoqiz.common.web.annotation.IdCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author luoqiz
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    private String msg = null;

    @Override
    public void initialize(IdCard constraintAnnotation) {
        this.msg = "[ %s ] 身份证校验失败";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isIdCard = IdcardUtil.isValidCard(value);
        if (isIdCard) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                String.format(this.msg, value)
        ).addConstraintViolation();
        return false;
    }


}