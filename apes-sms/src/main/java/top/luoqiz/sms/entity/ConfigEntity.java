package top.luoqiz.sms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * <p>
 * 短信配置表
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
@TableName("sms_config")
@Schema(name = "Config对象", description = "短信配置表")
public class ConfigEntity extends Model<ConfigEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("id")
    private Long id;

    @Schema(description = "配置名称")
    private String name;

    @Schema(description = "1 阿里云 2 腾讯云")
    private Integer platformType;

    @Schema(description = "访问密钥id")
    private String secretId;

    @Schema(description = "访问密钥")
    private String secretKey;

    @Schema(description = "应用id")
    private String appId;

    @Schema(description = "签名id")
    private String signName;

    @Schema(description = "模板code")
    private String templateCode;

    @Schema(description = "访问域名")
    private String domain;

    @Schema(description = "激活状态")
    private Boolean status;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        return "Config{" +
               "id=" + id +
               ", name=" + name +
               ", platformType=" + platformType +
               ", secretId=" + secretId +
               ", secretKey=" + secretKey +
               ", appId=" + appId +
               ", signName=" + signName +
               ", templateCode=" + templateCode +
               ", domain=" + domain +
               ", status=" + status +
               ", createTime=" + createTime +
               ", updateTime=" + updateTime +
               "}";
    }

}
