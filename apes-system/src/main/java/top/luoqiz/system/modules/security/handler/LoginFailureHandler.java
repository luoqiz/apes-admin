package top.luoqiz.system.modules.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.common.web.response.vo.RestResponse;
import top.luoqiz.system.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luoqiz
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    static Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        RestResponse result;
        //UserUtil.loginUsername(request);
        String username = "登录失败固定写死的用户";
        if (e instanceof AccountExpiredException) {
            // 账号过期
            log.info("[登录失败] - 用户[{}]账号过期", username);
            result = RestResponse.build(ResponseCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            log.info("[登录失败] - 用户[{}]密码错误", username);
            result = RestResponse.build(ResponseCode.USER_PASSWORD_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            // 密码过期
            log.info("[登录失败] - 用户[{}]密码过期", username);
            result = RestResponse.build(ResponseCode.USER_PASSWORD_EXPIRED);
        } else if (e instanceof DisabledException) {
            // 用户被禁用
            log.info("[登录失败] - 用户[{}]被禁用", username);
            result = RestResponse.build(ResponseCode.USER_DISABLED);
        } else if (e instanceof LockedException) {
            // 用户被锁定
            log.info("[登录失败] - 用户[{}]被锁定", username);
            result = RestResponse.build(ResponseCode.USER_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            // 内部错误
            log.error(String.format("[登录失败] - [%s]内部错误", username), e);
            result = RestResponse.fail(ResponseCode.USER_LOGIN_FAIL);
        } else {
            // 其他错误
            log.error(String.format("[登录失败] - [%s]其他错误", username), e);
            result = RestResponse.fail(ResponseCode.USER_LOGIN_FAIL);
        }
        // TODO 登录失败 记录日志
        ServletUtils.render(request, response, result);
    }
}