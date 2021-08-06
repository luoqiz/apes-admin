package top.luoqiz.common.web.annotation;

import top.luoqiz.common.web.validator.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>Title: IdCardValidator</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 身份证号校验
 * @date 2020/11/3 10:20
 * @since 1.1
 */

@Documented
@Constraint(validatedBy = IdCardValidator.class)
@Target({FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(IdCard.List.class)
public @interface IdCard {

    String message() default "";

    Class<?>[] groups() default {};

    String value() default "";

    Class<? extends Payload>[] payload() default {};

    @Target({FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        IdCard[] value();
    }

}
