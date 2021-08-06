package top.luoqiz.system.modules.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author luoqiz
 */
@Service(value = "al")
public class AlPermissionConfig {

    public Boolean check(String... permissions) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            // 获取当前用户的所有权限
            Collection<? extends GrantedAuthority> plist = authentication.getAuthorities();
            // 判断当前用户的所有权限是否包含接口上定义的权限
            for (GrantedAuthority grantedAuthority : plist) {
                for (String permission : permissions) {
                    if (StringUtils.equals(grantedAuthority.getAuthority(), permission)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}