package top.luoqiz.common.web.extend;

import java.util.LinkedHashMap;

/**
 * 用户信息扩展接口
 *
 * @author luoqiz
 * @date 2021-05-25
 */
public interface SysUserExtendService {

    /**
     * 扩展第三方系统的用户信息
     * 获取用户详细信息时，可以通过此方法放入其它信息
     *
     * @param sysUserEntity SysUserEntity类型用户信息
     * @return Object
     */
    Object getUserExtendInfo(Object sysUserEntity);

    /**
     * 扩展第三方系统的用户信息
     *
     * @param param 接口接收到的json参数，并添加 userId
     * @return boolean
     */
    boolean saveUserExtendInfo(LinkedHashMap<String, Object> param);

    /**
     * 扩展第三方系统用户信息，可自定义规则
     *
     * @param newPassword 新密码
     * @return boolean
     */
    boolean checkPassword(String newPassword);

    /**
     * 注册用户前的验证，例如需要短信验证码，验证通过
     * 若是不通过请直接抛出异常
     *
     * @param param 接口接收到的json参数
     */
    void registerPreUserValidate(LinkedHashMap<String, Object> param);
}
