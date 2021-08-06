package top.luoqiz.system.modules.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.luoqiz.user.entity.SysUserEntity;
import top.luoqiz.user.service.SysRoleService;
import top.luoqiz.user.service.SysUserService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author luoqiz
 */
@Component
public class SystemUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SystemUserDetailsServiceImpl.class);

    private SysUserService userService;

    private SysRoleService roleService;

    public SystemUserDetailsServiceImpl(SysUserService userService, SysRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("根据用户名查询用户信息" + username);
        SysUserEntity user = userService.getByUsername(username);
        List<GrantedAuthority> auths;
        if (user.getIsAdmin()) {
            auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        } else {
            //TODO 非管理员用户需要单独查询权限信息
            auths = roleService.mapToGrantedAuthorities(user);
        }
        SecurityUser securityUser = new SecurityUser(username, user.getPassword(),
                user.getEnabled(),
                user.getExpireTime() == null ? true : LocalDateTime.now().isAfter(user.getExpireTime()),
                true,
                user.getNonLocked() == null ? true : user.getNonLocked(),
                auths);
        securityUser.setUserId(user.getUserId());
        securityUser.setNickName(user.getNickName());
        securityUser.setAvatarPath(user.getAvatarPath());
        return securityUser;
    }

}