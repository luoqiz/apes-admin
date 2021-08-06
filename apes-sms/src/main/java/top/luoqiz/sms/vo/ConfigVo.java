package top.luoqiz.sms.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.sms.entity.ConfigEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
public class ConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long id;


    @Schema(description = "配置名称")
    @ExcelProperty(value = "配置名称", index = 1)
    private String name;


    @Schema(description = "1 阿里云 2 腾讯云")
    @ExcelProperty(value = "1 阿里云 2 腾讯云", index = 2)
    private Integer platformType;


    @Schema(description = "访问密钥id")
    @ExcelProperty(value = "访问密钥id", index = 3)
    private String secretId;


    @Schema(description = "访问密钥")
    @ExcelProperty(value = "访问密钥", index = 4)
    private String secretKey;


    @Schema(description = "应用id")
    @ExcelProperty(value = "应用id", index = 5)
    private String appId;


    @Schema(description = "签名id")
    @ExcelProperty(value = "签名id", index = 6)
    private String signName;


    @Schema(description = "模板code")
    @ExcelProperty(value = "模板code", index = 7)
    private String templateCode;


    @Schema(description = "访问域名")
    @ExcelProperty(value = "访问域名", index = 8)
    private String domain;


    @Schema(description = "激活状态")
    @ExcelProperty(value = "激活状态", index = 9)
    private Boolean status;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 10)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 11)
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


    public static ConfigVo fromEntity(ConfigEntity entity) {
        if (entity == null) {
            return null;
        }
        ConfigVo vo = new ConfigVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static List<ConfigVo> fromEntity(List<ConfigEntity> entities) {
        List<ConfigVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (ConfigEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
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
