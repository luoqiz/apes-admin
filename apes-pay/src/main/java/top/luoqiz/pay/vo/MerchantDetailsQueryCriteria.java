package top.luoqiz.pay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 支付应用信息 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-08
 */
public class MerchantDetailsQueryCriteria extends BasePageCriteria {

    @Schema(description = "列表id")
    private String detailsId;

    @Schema(description = "支付类型(支付渠道) 详情查看com.egzosn.pay.spring.boot.core.merchant.PaymentPlatform对应子类，aliPay 支付宝， wxPay微信..等等")
    private String payType;

    @Schema(description = "应用id")
    private String appid;

    @Schema(description = "商户id，商户号，合作伙伴id等等")
    private String mchId;

    @Schema(description = "子appid")
    private String subAppId;

    @Schema(description = "子商户id")
    private String subMchId;

    @Schema(description = "是否为测试环境: 0 否，1 测试环境")
    private Boolean isTest;


    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }


    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }


    public String getSubAppId() {
        return subAppId;
    }

    public void setSubAppId(String subAppId) {
        this.subAppId = subAppId;
    }


    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }


    public Boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }


}