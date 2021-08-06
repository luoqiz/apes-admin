package top.luoqiz.system.modules.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import top.luoqiz.common.web.response.vo.RestResponse;
import top.luoqiz.system.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luoqiz
 */
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // TODO 登出成功 记录登出日志
        ServletUtils.render(request, response, RestResponse.success(true));
    }
}