package top.luoqiz.system.modules.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Service
public class TokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
//    @Resource
//    TokenUtils tokenUtils;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        // 存储 Token 的 Headers Key与 Value，默认是 Authorization
//        final String authorizationKey = "Authorization";
//        String authorizationValue;
//        try {
//            authorizationValue = request.getHeader(authorizationKey);
//        } catch (Exception e) {
//            authorizationValue = null;
//        }
//        // Token 开头部分 默认 Bearer 开头
//        String bearer = "Bearer ";
//        if (authorizationValue != null && authorizationValue.startsWith(bearer)) {
//            // token
//            String token = authorizationValue.substring(bearer.length());
//            SysUser sysUser = tokenUtils.validationToken(token);
//            if (sysUser != null) {
//                // Spring Security 角色名称默认使用 "ROLE_" 开头
//                // authorities.add 可以增加多个用户角色，对于一个用户有多种角色的系统来说，
//                // 可以通过增加用户角色表、用户--角色映射表，存储多个用户角色信息
//                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + sysUser.getUserRole()));
//                // 传入用户名、用户密码、用户角色。 这里的密码随便写的，用不上
//                UserDetails userDetails = new User(sysUser.getUsername(), "password", authorities);
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(userDetails.getUsername());
//                // 授权
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
}