package top.luoqiz.system.modules.security.token.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luoqiz
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public TokenAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        boolean debug = this.logger.isDebugEnabled();
        String token = readTokenFromRequest(request);
        if (StringUtils.isBlank(token)) {
            throw new UsernameNotFoundException("token not found");
        }

        if (debug) {
            this.logger.debug("Token Authentication Authorization header found ");
        }

        //token包装类, 使用principal来装载token
        UsernamePasswordAuthenticationToken tokenAuthenticationToken = new UsernamePasswordAuthenticationToken(
                token, null);

        //AuthenticationManager 负责解析
        Authentication authResult = getAuthenticationManager()
                .authenticate(tokenAuthenticationToken);
        if (debug) {
            this.logger.debug("Authentication success: " + authResult);
        }

        return authResult;
    }

    private String readTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeaders("token").toString();
        if (token != null) {
            return token;
        }
        token = request.getHeaders("x-token").toString();
        if (token != null) {
            return token;
        }
        token = request.getParameter("token");
        return token;
    }

}