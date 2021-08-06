package top.luoqiz.system.modules.security.token.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;
import top.luoqiz.system.modules.security.token.repository.TokenRepository;
import top.luoqiz.system.utils.TokenInfo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 仿照 {@link org.springframework.security.web.context.SecurityContextPersistenceFilter} 编写
 *
 * @author luoqiz
 */
public class SecurityContextPersistenceTokenFilter extends GenericFilterBean {

    static final String FILTER_APPLIED = "___spring_security_scpf_applied";

    private TokenRepository tokenRepository;

    public SecurityContextPersistenceTokenFilter(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // ensure that filter is only applied once per request
        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

        // 读取token
        String token = readTokenFromRequest(request);

        // token 不为空，认为已登录
        if (StringUtils.isNotBlank(token)) {
            Optional<TokenInfo> tokenInfoOptional = tokenRepository.getTokenInfo(token);
            if (tokenInfoOptional.isPresent()) {
                TokenInfo tokenInfo = tokenInfoOptional.get();
                Set<SimpleGrantedAuthority> grantedAuthority = tokenInfo.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
                User principal = new User(tokenInfo.getUserName(), "******", grantedAuthority);
                Authentication authentication = new UsernamePasswordAuthenticationToken(principal, token, grantedAuthority);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // Token 续期
                tokenRepository.checkRenewal(token);
            }

        }
        chain.doFilter(request, response);
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
