package top.luoqiz.system.modules.security.rest;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.common.web.annotation.AnonymousAccess;
import top.luoqiz.common.web.extend.SysUserExtendService;
import top.luoqiz.common.web.response.config.BusinessException;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.user.entity.SysUserEntity;
import top.luoqiz.user.service.SysUserService;
import top.luoqiz.user.vo.SysUserVo;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-03
 */
@Tag(name = "用户安全接口")
@RestController
@RequestMapping("/user")
public class SecurityUserController {

    private SysUserService sysUserService;
    private SysUserExtendService sysUserExtendService;
    private PasswordEncoder passwordEncoder;
    private Validator validator;

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired(required = false)
    public void setUserExtendService(SysUserExtendService sysUserExtendService) {
        this.sysUserExtendService = sysUserExtendService;
    }

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 获取用户信息
     *
     * @param authentication 认证信息
     * @return SysUserVo
     */
    @Operation(summary = "获取用户信息")
    @GetMapping("/info")
    public SysUserVo info(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        SysUserEntity user = sysUserService.getByUsername(authentication.getName());
        SysUserVo userVo = SysUserVo.fromEntity(user);
        if (sysUserExtendService != null) {
            try {
                userVo.setUserExtend(sysUserExtendService.getUserExtendInfo(user));
            } catch (Exception e) {
                userVo.setUserExtend(null);
                e.printStackTrace();
            }
        }
        return userVo;
    }

    @Operation(summary = "用户注册")
    @AnonymousAccess
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public boolean register(@RequestBody LinkedHashMap<String, Object> param) throws JsonProcessingException {
        // 用户指定检测是否可以进行注册
        sysUserExtendService.registerPreUserValidate(param);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SysUserEntity user = objectMapper.readValue(objectMapper.writeValueAsString(param), SysUserEntity.class);
        // 验证用户是否已经存在
        SysUserEntity dbUser = sysUserService.getByUsername(user.getPhone());
        if (dbUser != null) {
            throw new BusinessException(ResponseCode.USER_IS_EXIST);
        }

        Long userId = IdWorker.getId();
        param.put("userId", userId);
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(param.getOrDefault("password", "123456").toString()));
        user.setIsAdmin(false);
        user.setUsername(user.getPhone());
        user.setCreateTime(LocalDateTime.now());
        checkUser(user);
        boolean flag = true;
        if (sysUserExtendService != null) {
            flag = sysUserExtendService.saveUserExtendInfo(param);
        }
        if (flag) {
            return sysUserService.save(user);
        }
        throw new BusinessException(ResponseCode.USER_ACCOUNT_EXPIRED);
    }

    @Operation(summary = "用户修改密码")
    @AnonymousAccess
    @PostMapping("/password/modify")
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(Authentication authentication, String newPassword) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        SysUserEntity user = sysUserService.getByUsername(authentication.getName());
        SysUserEntity mUser = new SysUserEntity();
        //此处检测密码规则
        if (StringUtils.isBlank(newPassword)) {
            throw new BusinessException(ResponseCode.UPDATE_USER_PASSWORD_NON_COMPLIANT_RULE);
        }

        boolean flag = true;
        if (sysUserExtendService != null) {
            flag = sysUserExtendService.checkPassword(newPassword);
        }
        mUser.setUserId(user.getUserId());
        mUser.setPassword(passwordEncoder.encode(newPassword));
        mUser.setPwdResetTime(LocalDateTime.now());
        if (flag) {
            return sysUserService.updateById(mUser);
        }
        throw new BusinessException(ResponseCode.UPDATE_USER_PASSWORD_NON_COMPLIANT_RULE);
    }

    @Operation(summary = "用户修改头像")
    @AnonymousAccess
    @PostMapping("/avatar")
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAvatar(Authentication authentication, String avatarPath) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        SysUserEntity user = sysUserService.getByUsername(authentication.getName());
        SysUserEntity mUser = new SysUserEntity();
        if (StringUtils.isNotBlank(avatarPath)) {
            mUser.setUserId(user.getUserId());
            mUser.setAvatarPath(avatarPath);
            return sysUserService.updateById(mUser);
        }
        return false;
    }

    /**
     * 检测注册用户信息填写是否完整
     *
     * @param user 生成的用户实体类
     */
    private void checkUser(SysUserEntity user) {
        Set<ConstraintViolation<SysUserEntity>> constraintViolations =
                validator.validate(user);
        for (ConstraintViolation<SysUserEntity> cs : constraintViolations) {
            String msg = cs.getPropertyPath() + ":参数有误";
            throw new BusinessException(ResponseCode.GLOBAL_PARAM_ERROR.getCode(), String.format("[%s] %s", msg, cs.getMessage()));
        }
    }

}
