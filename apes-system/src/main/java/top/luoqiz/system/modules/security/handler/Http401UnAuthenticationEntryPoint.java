package top.luoqiz.system.modules.security.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.common.web.response.vo.RestResponse;
import top.luoqiz.system.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>Title: UnAuthenticationEntryPoint</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2021/3/3 9:56
 * @since 1.0
 */

@Component
public class Http401UnAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("认证失败：" + authException.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // TODO 未登录访问 记录日志
        ServletUtils.render(request, response, RestResponse.fail(ResponseCode.USER_NEED_LOGIN));
    }
}
