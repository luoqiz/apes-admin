package top.luoqiz.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-20
 */
@TableName("sys_user")
@Schema(name = "SysUser对象", description = "系统用户")
public class SysUserEntity extends Model<SysUserEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("user_id")
    private Long userId;

    @Schema(description = "部门名称")
    private Long deptId;

    @Schema(description = "用户名")
    private String username;

    @NotEmpty(message = "用户昵称不允许为空")
    @Schema(description = "昵称")
    private String nickName;

    @NotEmpty(message = "性别不允许为空")
    @Schema(description = "性别")
    private String gender;

    @NotEmpty(message = "手机号码不允许为空")
    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像地址")
    private String avatarName;

    @Schema(description = "头像真实路径")
    private String avatarPath;

    @NotEmpty(message = "密码不允许为空")
    @Schema(description = "密码")
    private String password;

    @Schema(description = "是否为admin账号")
    private Boolean isAdmin;

    @Schema(description = "状态：1启用、0禁用")
    private Boolean enabled;

    @Schema(description = "账号到期时间")
    private LocalDateTime expireTime;

    @Schema(description = "账号是否未锁定")
    private Boolean nonLocked;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "修改密码的时间")
    private LocalDateTime pwdResetTime;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getPwdResetTime() {
        return pwdResetTime;
    }

    public void setPwdResetTime(LocalDateTime pwdResetTime) {
        this.pwdResetTime = pwdResetTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                ", username=" + username +
                ", nickName=" + nickName +
                ", gender=" + gender +
                ", phone=" + phone +
                ", email=" + email +
                ", avatarName=" + avatarName +
                ", avatarPath=" + avatarPath +
                ", password=" + password +
                ", isAdmin=" + isAdmin +
                ", enabled=" + enabled +
                ", expireTime=" + expireTime +
                ", nonLocked=" + nonLocked +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", pwdResetTime=" + pwdResetTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }

}
