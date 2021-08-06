package top.luoqiz.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: ClientIp</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 获取客户端真实IP
 * @date 2020/11/17 11:46
 * @since 1.1
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientIp {
}
