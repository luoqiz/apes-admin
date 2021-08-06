package top.luoqiz.system.modules.security.token.repository;

import top.luoqiz.system.utils.TokenInfo;

import java.util.Optional;

/**
 * token 信息保存
 *
 * @author luoqiz
 */
public interface TokenRepository {

    /**
     * 创建token
     *
     * @param userId   用户Id
     * @param username 用户名
     * @return
     */
    TokenInfo createToken(String userId, String username);

    /**
     * 保存token信息
     *
     * @param token TokenInfo
     */
    void saveToken(TokenInfo token);

    /**
     * 获取token信息
     *
     * @param token token字符串
     * @return
     */
    Optional<TokenInfo> getTokenInfo(String token);

    /**
     * token 续期
     *
     * @param token token字符串
     */
    void checkRenewal(String token);

    /**
     * 删除token
     *
     * @param token token字符串
     * @return
     */
    boolean removeToken(String token);
}
