package top.luoqiz.system.modules.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.luoqiz.common.web.response.vo.RestResponse;
import top.luoqiz.system.modules.security.SecurityUser;
import top.luoqiz.system.modules.security.token.repository.TokenRepository;
import top.luoqiz.system.utils.ServletUtils;
import top.luoqiz.system.utils.TokenInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author luoqiz
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // TODO 登录成功 记录日志
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        TokenInfo token = tokenRepository.createToken(user.getUserId() + "", authentication.getName());
        token.setAuthorities(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        tokenRepository.saveToken(token);
        user.setTokenInfo(token);
        ServletUtils.render(request, response, RestResponse.success(user));
    }
}