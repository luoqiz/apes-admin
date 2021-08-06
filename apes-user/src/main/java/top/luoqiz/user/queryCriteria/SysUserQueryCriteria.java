package top.luoqiz.user.queryCriteria;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-20
 */
public class SysUserQueryCriteria extends BasePageCriteria {

    @Schema(description = "部门名称")
    private Long deptId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "是否为admin账号")
    private Boolean isAdmin;

    @Schema(description = "状态：1启用、0禁用")
    private Boolean enabled;

    @Schema(description = "账号到期时间")
    private LocalDateTime expireTime;

    @Schema(description = "账号是否未锁定")
    private Boolean nonLocked;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }


    public Boolean getNonLocked() {
        return nonLocked;
    }

    public void setNonLocked(Boolean nonLocked) {
        this.nonLocked = nonLocked;
    }


}