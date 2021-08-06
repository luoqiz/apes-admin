package top.luoqiz.common.web.validator;

import top.luoqiz.common.web.annotation.In;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luoqiz
 */
public class InValidator implements ConstraintValidator<In, Object> {
    private final Set<Object> values = new HashSet<>();
    private String msg = null;

    @Override
    public void initialize(In constraintAnnotation) {
        Class<? extends Enum> clz = constraintAnnotation.enums();
        if (clz.getSuperclass() == Enum.class) {
            Object[] enumConstants = clz.getEnumConstants();
            for (Object enumConstant : enumConstants) {
                this.values.add(enumConstant.toString());
            }
        }

        for (Object value : constraintAnnotation.values()) {
            this.values.add(value);
        }
        String msg = values.stream().map(Object::toString).collect(Collectors.joining(",", "[", "]"));
        this.msg = msg;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String vs = value.toString();
        boolean contains = values.contains(vs);
        if (contains) {
            return true;
        }

        if (context.getDefaultConstraintMessageTemplate().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("取值范围为：" + this.msg).addConstraintViolation();
        } else {
            if (context.getDefaultConstraintMessageTemplate().contains("%s")) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        String.format(context.getDefaultConstraintMessageTemplate(), msg)
                ).addConstraintViolation();
            }
        }
        return false;
    }


}