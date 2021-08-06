package top.luoqiz.common.web.annotation;

import top.luoqiz.common.web.validator.InValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author luoqiz
 */

@Documented
@Constraint(validatedBy = InValidator.class)
@Target({FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(In.List.class)
public @interface In {

    String message() default "";

    Class<?>[] groups() default {};

    String[] values() default {};

    Class<? extends Enum> enums() default Enum.class;

    Class<? extends Payload>[] payload() default {};

    @Target({FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        In[] value();
    }
}
