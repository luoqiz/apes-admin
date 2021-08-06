package top.luoqiz.common.web.response.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.luoqiz.common.web.annotation.IgnoreWrapper;
import top.luoqiz.common.web.response.vo.BaseResponse;
import top.luoqiz.common.web.response.vo.RestResponse;

/**
 * 全局返回值统一封装
 *
 * @author luoqiz
 */
@Configuration
public class GlobalReturnConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @RestControllerAdvice(basePackages = {"top.luoqiz"})
    class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

//        private final static String PACKAGE_PATH = "com.csdata.lianduoduo";

        /**
         * 拦截之前业务处理，请求先到supports再到beforeBodyWrite
         * <p>
         * 用法1：自定义是否拦截。若方法名称（或者其他维度的信息）在指定的常量范围之内，则不拦截。
         *
         * @param methodParameter
         * @param aClass
         * @return 返回true会执行拦截；返回false不执行拦截
         */
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            // 过滤
            return null == methodParameter.getMethodAnnotation(IgnoreWrapper.class)
                    && !methodParameter.getMethod().getReturnType().isAssignableFrom(Void.TYPE)
                    && !methodParameter.getMethod().getReturnType().isAssignableFrom(BaseResponse.class)
//                    && !methodParameter.getDeclaringClass().getPackage().getName().contains(PACKAGE_PATH)
                    ;
        }

        /**
         * 向客户端返回响应信息之前的业务逻辑处理
         * <p>
         * 用法1：无论controller返回什么类型的数据，在写入客户端响应之前统一包装，客户端永远接收到的是约定格式的内容
         * <p>
         * 用法2：在写入客户端响应之前统一加密
         *
         * @param responseObject     响应内容
         * @param methodParameter
         * @param mediaType
         * @param aClass
         * @param serverHttpRequest
         * @param serverHttpResponse
         * @return
         */
        @Override
        public Object beforeBodyWrite(Object responseObject, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            // responseObject是否为null
            if (null == responseObject) {
                return RestResponse.success();
            }
            //responseObject是否是文件
            if (responseObject instanceof Resource) {
                return responseObject;
            }
            //该方法返回值类型是否是void
            String returnType = "void";
            if (returnType.equals(methodParameter.getParameterType().getName())) {
                return RestResponse.success();
            }
            if (methodParameter.getMethod().getReturnType().isAssignableFrom(Void.TYPE)) {
                return RestResponse.success();
            }

            //处理string类型的返回值
            //当返回类型是String时，用的是StringHttpMessageConverter转换器，无法转换为Json格式
            //必须在方法体上标注RequestMapping(produces = "application/json; charset=UTF-8")
            if (responseObject instanceof String) {
                try {
                    return objectMapper.writeValueAsString(RestResponse.success(responseObject));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            //该方法返回的媒体类型是否是application/json。若不是，直接返回响应内容
            if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
                return responseObject;
            }
            //该方法返回值类型是否是R。若是直接返回，无需再包装一层
            if (responseObject instanceof BaseResponse) {
                return responseObject;
            }
            return RestResponse.success(responseObject);
        }
    }
}

