package top.luoqiz.common.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.luoqiz.common.web.annotation.ClientIp;

import javax.servlet.ServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Title: ClientIpResolver</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description {@link top.luoqiz.common.web.annotation.ClientIp} 解析器
 * @date 2020/11/17 11:48
 * @since 1.1
 */
public class ClientIpResolver implements HandlerMethodArgumentResolver {

    private static final String[] IP_HEADER_CANDIDATES = {
            // Squid 服务代理
            "X-Forwarded-For",
            // apache 服务代理
            "Proxy-Client-IP",
            // weblogic 服务代理
            "WL-Proxy-Client-IP",
            // X-Real-IP：nginx服务代理
            "X-Real-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            // 有些代理服务器
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    @Override
    public boolean supportsParameter(MethodParameter param) {
        return param.getParameterType().equals(String.class) &&
                param.hasParameterAnnotation(ClientIp.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        // 提取header得到IP地址列表（多重代理场景），取第一个IP
        for (String header : IP_HEADER_CANDIDATES) {
            String ipList = webRequest.getHeader(header);
            if (ipList != null && ipList.length() != 0 &&
                    !"unknown".equalsIgnoreCase(ipList)) {
                return ipList.split(",")[0];
            }
        }

        // 没有经过代理或者SLB，直接 getRemoteAddr 方法获取IP
        String ip = ((ServletRequest) webRequest.getNativeRequest()).getRemoteAddr();

        // 如果是本地环回IP，则根据网卡取本机配置的IP
        String localIpv4 = "127.0.0.1";
        String localIpv6 = "0:0:0:0:0:0:0:1";
        if (localIpv4.equals(ip) || localIpv6.equals(ip)) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                return inetAddress.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return ip;
            }
        }
        return ip;
    }
}
