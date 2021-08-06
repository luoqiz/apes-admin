package top.luoqiz.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.user.entity.SysUserEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long userId;


    @Schema(description = "部门名称")
    @ExcelProperty(value = "部门名称", index = 1)
    private Long deptId;


    @Schema(description = "用户名")
    @ExcelProperty(value = "用户名", index = 2)
    private String username;


    @Schema(description = "昵称")
    @ExcelProperty(value = "昵称", index = 3)
    private String nickName;


    @Schema(description = "性别")
    @ExcelProperty(value = "性别", index = 4)
    private String gender;


    @Schema(description = "手机号码")
    @ExcelProperty(value = "手机号码", index = 5)
    private String phone;


    @Schema(description = "邮箱")
    @ExcelProperty(value = "邮箱", index = 6)
    private String email;


    @Schema(description = "头像地址")
    @ExcelProperty(value = "头像地址", index = 7)
    private String avatarName;


    @Schema(description = "头像真实路径")
    @ExcelProperty(value = "头像真实路径", index = 8)
    private String avatarPath;

    @Schema(description = "是否为admin账号")
    @ExcelProperty(value = "是否为admin账号", index = 10)
    private Boolean isAdmin;


    @Schema(description = "状态：1启用、0禁用")
    @ExcelProperty(value = "状态：1启用、0禁用", index = 11)
    private Boolean enabled;


    @Schema(description = "账号到期时间")
    @ExcelProperty(value = "账号到期时间", index = 12)
    private LocalDateTime expireTime;


    @Schema(description = "账号是否未锁定")
    @ExcelProperty(value = "账号是否未锁定", index = 13)
    private Boolean nonLocked;


    @Schema(description = "创建者")
    @ExcelProperty(value = "创建者", index = 14)
    private String createBy;


    @Schema(description = "更新者")
    @ExcelProperty(value = "更新者", index = 15)
    private String updateBy;


    @Schema(description = "修改密码的时间")
    @ExcelProperty(value = "修改密码的时间", index = 16)
    private LocalDateTime pwdResetTime;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 17)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 18)
    private LocalDateTime updateTime;

    @Schema(description = "扩展第三方使用时用户信息")
    @ExcelProperty(value = "扩展用户信息", index = 18)
    private Object userExtend;


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

    public static SysUserVo fromEntity(SysUserEntity entity) {
        if (entity == null) {
            return null;
        }
        SysUserVo vo = new SysUserVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<SysUserVo> fromEntity(List<SysUserEntity> entities) {
        LinkedList<SysUserVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysUserEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
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
                ", isAdmin=" + isAdmin +
                ", enabled=" + enabled +
                ", expireTime=" + expireTime +
                ", nonLocked=" + nonLocked +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", pwdResetTime=" + pwdResetTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userExtend=" + userExtend.toString() +
                "}";
    }


    public Object getUserExtend() {
        return userExtend;
    }

    public void setUserExtend(Object userExtend) {
        this.userExtend = userExtend;
    }
}
