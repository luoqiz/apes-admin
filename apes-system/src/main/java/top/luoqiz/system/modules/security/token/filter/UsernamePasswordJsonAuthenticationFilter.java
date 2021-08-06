package top.luoqiz.system.modules.security.token.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>Title: UsernamePasswordJsonAuthenticationFilter</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description ajax 用户名密码登录
 * @date 2021/1/29 11:40
 * @since 1.0
 */
public class UsernamePasswordJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println(request.getMethod());
        if (postOnly && !request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        //Content-Type is json 自己去实现 json解析
        if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream ins = request.getInputStream()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map param = objectMapper.readValue(ins, Map.class);
                String username = param.getOrDefault(getUsernameParameter(), "").toString().trim();
                String password = param.getOrDefault(getPasswordParameter(), "").toString();
                authRequest = new UsernamePasswordAuthenticationToken(username, password);
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken("", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }

        //transmit it to UsernamePasswordAuthenticationFilter
        else {
            return super.attemptAuthentication(request, response);
        }
    }
}