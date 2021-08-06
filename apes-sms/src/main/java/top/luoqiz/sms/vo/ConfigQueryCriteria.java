package top.luoqiz.sms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 短信配置表 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-06
 */
public class ConfigQueryCriteria extends BasePageCriteria {

    @Schema(description = "1 阿里云 2 腾讯云")
    private Integer platformType;

    @Schema(description = "签名id")
    private String signName;

    @Schema(description = "模板code")
    private String templateCode;



    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
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



}