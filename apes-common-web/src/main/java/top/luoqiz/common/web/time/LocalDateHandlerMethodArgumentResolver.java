package top.luoqiz.common.web.time;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 配置 非注解方式传入的 LocalDate 类型参数的处理器
 *
 * @author luoqiz
 */
public class LocalDateHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return LocalDate.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
        HttpServletRequest httpServletRequest = servletWebRequest.getRequest();
        String key = parameter.getParameter().getName();
        String value = httpServletRequest.getParameter(key);
        // 获取 contentType
//        String contentType = httpServletRequest.getContentType();
        // 获取支持媒体类型
//        MediaType mediaType = MediaType.parseMediaType(contentType);
//        Charset charset = mediaType.getCharset();
//        charset = charset == null ? StandardCharsets.UTF_8 : charset;
//        ServletInputStream in = httpServletRequest.getInputStream();

        return convert(value);
    }

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String timeStampFormat = "^\\d+$";

    public LocalDate convert(String value) {

        if (value == null || value.trim().equals("") || value.equalsIgnoreCase("null")) {
            return null;
        }

        value = value.trim();

        try {
            if (value.contains("-")) {
                DateTimeFormatter formatter;
                if (value.contains(":")) {
                    formatter = DateTimeFormatter.ofPattern(dateFormat);
                } else {
                    formatter = DateTimeFormatter.ofPattern(shortDateFormat);
                }
                return LocalDate.parse(value, formatter);
            } else if (value.matches(timeStampFormat)) {
                Long timestamp = Long.valueOf(value);
                return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.systemDefault()).toLocalDate();
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to LocalDate fail", value));
        }
        throw new RuntimeException(String.format("parser %s to LocalDate fail", value));
    }
}
