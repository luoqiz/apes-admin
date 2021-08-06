package top.luoqiz.system.modules.security.token.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import top.luoqiz.system.utils.TokenInfo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>Title: TokenRepository</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description token 处理工具类
 * @date 2021/3/2 19:20
 * @since 1.0
 */
@Configuration
@ConditionalOnMissingBean(name = "tokenRepository")
public class InMemoryTokenRepository implements TokenRepository {

    public static CopyOnWriteArrayList<TokenInfo> tokenInfos = new CopyOnWriteArrayList<>();

    /**
     * 创建token
     *
     * @return
     */
    @Override
    public TokenInfo createToken(String userId, String username) {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        tokenInfo.setUserId(userId);
        tokenInfo.setUserName(username);
        tokenInfo.setExpired(LocalDateTime.now().plusHours(1));
        return tokenInfo;
    }

    /**
     * 保存token信息
     */
    @Override
    public void saveToken(TokenInfo token) {
        tokenInfos.add(token);
    }

    /**
     * 校验token信息
     */
    @Override
    public Optional<TokenInfo> getTokenInfo(String token) {
        Optional<TokenInfo> userInfo = tokenInfos.stream().parallel().filter(tokenInfo -> tokenInfo.getToken().equals(token)).findFirst();
        if (userInfo.isPresent()) {
            if (userInfo.get().getExpired().isBefore(LocalDateTime.now())) {
                tokenInfos.remove(token);
                return Optional.empty();
            }
        }
        return userInfo;
    }

    @Override
    public void checkRenewal(String token) {
        Optional<TokenInfo> userInfo = tokenInfos.stream().parallel().filter(tokenInfo -> tokenInfo.getToken().equals(token)).findFirst();
        userInfo.ifPresent(user -> user.setExpired(user.getExpired().plusHours(1)));
    }

    @Override
    public boolean removeToken(String token) {
        return tokenInfos.removeIf(tokenInfo -> tokenInfo.getToken().equals(token));
    }
}
