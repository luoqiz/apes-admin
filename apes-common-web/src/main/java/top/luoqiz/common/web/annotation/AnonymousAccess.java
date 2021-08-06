package top.luoqiz.common.web.annotation;

import java.lang.annotation.*;

/**
 * <p>Title: Anonymous</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 匿名访问注解
 * @date 2021/3/3 13:32
 * @since 1.0
 */
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
