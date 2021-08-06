package top.luoqiz.common.web.resolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数解析配置
 *
 * @author luoqiz
 */
@Configuration
public class HandlerMethodArgumentResolverConfig {

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public HandlerMethodArgumentResolverConfig(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @PostConstruct
    private void addArgumentResolvers() {
        // 获取到的是不可变的集合
        List<HandlerMethodArgumentResolver> argumentResolvers =
                requestMappingHandlerAdapter.getArgumentResolvers();
        RequestBodyHandlerMethodArgumentResolver requestBodyHandlerMethodArgumentResolver =
                requestBodyHandlerMethodArgumentResolver(argumentResolvers);
        // ha.getArgumentResolvers()获取到的是不可变的集合,所以我们需要新建一个集合来放置参数解析器
        List<HandlerMethodArgumentResolver> myArgumentResolvers =
                new ArrayList<>(argumentResolvers.size() + 1);
        // 将自定义的解析器，放置在第一个,并保留原来的解析器
        myArgumentResolvers.add(requestBodyHandlerMethodArgumentResolver);
        myArgumentResolvers.addAll(argumentResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(myArgumentResolvers);
    }


    public RequestBodyHandlerMethodArgumentResolver requestBodyHandlerMethodArgumentResolver(
            List<HandlerMethodArgumentResolver> resolvers) {
        // 解析 content-type=application/json 的默认解析器
        RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor = null;
        // 解析 content-type=application/x-www-form-urlencoded 的默认解析器
        RequestParamMapMethodArgumentResolver requestParamMapMethodArgumentResolver = null;

        if (resolvers == null) {
            throw new RuntimeException("argumentResolverList must not be null!");
        }
        for (HandlerMethodArgumentResolver argumentResolver : resolvers) {
            if (requestResponseBodyMethodProcessor != null && requestParamMapMethodArgumentResolver != null) {
                break;
            }
            if (argumentResolver instanceof RequestResponseBodyMethodProcessor) {
                requestResponseBodyMethodProcessor = (RequestResponseBodyMethodProcessor) argumentResolver;
                continue;
            }
            if (argumentResolver instanceof RequestParamMapMethodArgumentResolver) {
                requestParamMapMethodArgumentResolver = (RequestParamMapMethodArgumentResolver) argumentResolver;
            }
        }
        if (requestResponseBodyMethodProcessor == null || requestParamMapMethodArgumentResolver == null) {
            throw new RuntimeException("requestResponseBodyMethodProcessor and "
                    + " requestParamMapMethodArgumentResolver must not be null!");
        }
        return new RequestBodyHandlerMethodArgumentResolver(requestResponseBodyMethodProcessor,
                requestParamMapMethodArgumentResolver);

    }

}
