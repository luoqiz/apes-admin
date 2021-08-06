package top.luoqiz.common.web.resolver;

import cn.hutool.http.ContentType;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述： @RequestBody 注解默认只处理 content-type=application/json 的参数，其解析器为 {@link org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor}
 * 在此重写，使其支持 content-type=application/x-www-form-urlencoded 的参数 ,其默认解析器为 {@link org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver}
 * <p>
 * 思路 ： 通过判断 content-type ，调用其相对应的默认解析器
 *
 * @author luoqiz
 */
public class RequestBodyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    final String formUrlencoded = ContentType.FORM_URLENCODED.getValue();

    /**
     * 解析 content-type=application/json 的默认解析器是RequestResponseBodyMethodProcessor
     */
    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

    /**
     * 解析 content-type=application/x-www-form-urlencoded 的默认解析器是 RequestParamMapMethodArgumentResolver
     */
    private RequestParamMapMethodArgumentResolver requestParamMapMethodArgumentResolver;

    /**
     * 全参构造
     */
    public RequestBodyHandlerMethodArgumentResolver(RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor,
                                                    RequestParamMapMethodArgumentResolver requestParamMapMethodArgumentResolver) {
        this.requestResponseBodyMethodProcessor = requestResponseBodyMethodProcessor;
        this.requestParamMapMethodArgumentResolver = requestParamMapMethodArgumentResolver;
    }

    /**
     * 当参数前有 @RequestBody 注解时， 解析该参数 会使用此 解析器
     * <p>
     * 注:此方法的返回值将决定:是否使用此解析器解析该参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestBody.class);
    }

    /**
     * 解析参数
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory)
            throws Exception {

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        if (request == null) {
            throw new RuntimeException(" request must not be null!");
        }
        String contentType = request.getContentType();
        /*
         * 如果ContentType是application/x-www-form-urlencoded，那么使用ServletModelAttributeMethodProcessor解析器
         *
         * 注:其实默认的，当系统识别到参数前有@RequestBody注解时，就会走RequestResponseBodyMethodProcessor解析器;这里就
         *    相当于在走默认的解析器前走了个判断而已。
         */
        if (formUrlencoded.equals(contentType)) {
            return requestParamMapMethodArgumentResolver.resolveArgument(methodParameter,
                    modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }
        return requestResponseBodyMethodProcessor.resolveArgument(methodParameter,
                modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
    }

}
