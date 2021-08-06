package top.luoqiz.system.utils;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * <p>Title: TokenInfo </p>
 * 用户信息
 *
 * @author luoqiz
 * @version 1.0
 * @date 2021/3/2 19:49
 * @since 1.0
 */

public class TokenInfo {

    private String token;
    private String userId;
    private String userName;
    private LocalDateTime expired;
    private Collection<String> authorities;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }


}
