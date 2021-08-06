package top.luoqiz.system.modules.security.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import top.luoqiz.system.modules.security.token.repository.TokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author luoqiz
 */
public class TokenLogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

    private final TokenRepository tokenRepository;

    public TokenLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 读取token
        String token = readTokenFromRequest(request);
        // token 不为空，认为已登录
        if (StringUtils.isNotBlank(token)) {
            tokenRepository.removeToken(token);
        }
    }

    private String readTokenFromRequest(HttpServletRequest request) {
        Enumeration<String> headerToken = request.getHeaders("token");
        String token = headerToken.hasMoreElements() ? headerToken.nextElement() : "";
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        Enumeration<String> headerXToken = request.getHeaders("x-token");
        token = headerXToken.hasMoreElements() ? headerXToken.nextElement() : "";
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        Enumeration<String> headerAuthorizationToken = request.getHeaders("Authorization");
        token = headerAuthorizationToken.hasMoreElements() ? headerAuthorizationToken.nextElement() : "";
        if (StringUtils.isNotBlank(token)) {
            if (StringUtils.startsWith(token, "Bearer ")) {
                return token.substring(7);
            }
            return token;
        }
        token = request.getParameter("token");
        return token;
    }
}