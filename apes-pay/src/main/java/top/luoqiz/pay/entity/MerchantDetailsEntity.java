package top.luoqiz.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 支付应用信息
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-08
 */
@TableName("merchant_details")
@Schema(name = "MerchantDetails对象", description = "支付应用信息")
public class MerchantDetailsEntity extends Model<MerchantDetailsEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "列表id")
    @TableId("details_id")
    private String detailsId;

    @Schema(description = "支付类型(支付渠道) 详情查看com.egzosn.pay.spring.boot.core.merchant.PaymentPlatform对应子类，aliPay 支付宝， wxPay微信..等等")
    private String payType;

    @Schema(description = "应用id")
    private String appid;

    @Schema(description = "商户id，商户号，合作伙伴id等等")
    private String mchId;

    @Schema(description = "当前面私钥公钥为证书类型的时候，这里必填，可选值:PATH,STR,INPUT_STREAM")
    private String certStoreType;

    @Schema(description = "私钥或私钥证书")
    private String keyPrivate;

    @Schema(description = "公钥或公钥证书")
    private String keyPublic;

    @Schema(description = "key证书,附加证书使用，如SSL证书，或者银联根级证书方面")
    private String keyCert;

    @Schema(description = "私钥证书或key证书的密码")
    private String keyCertPwd;

    @Schema(description = "异步回调")
    private String notifyUrl;

    @Schema(description = "同步回调地址，大部分用于付款成功后页面转跳")
    private String returnUrl;

    @Schema(description = "签名方式,目前已实现多种签名方式详情查看com.egzosn.pay.common.util.sign.encrypt。MD5,RSA等等")
    private String signType;

    @Schema(description = "收款账号，暂时只有支付宝部分使用，可根据开发者自行使用")
    private String seller;

    @Schema(description = "子appid")
    private String subAppId;

    @Schema(description = "子商户id")
    private String subMchId;

    @Schema(description = "编码类型，大部分为utf-8")
    private String inputCharset;

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

    public String getCertStoreType() {
        return certStoreType;
    }

    public void setCertStoreType(String certStoreType) {
        this.certStoreType = certStoreType;
    }

    public String getKeyPrivate() {
        return keyPrivate;
    }

    public void setKeyPrivate(String keyPrivate) {
        this.keyPrivate = keyPrivate;
    }

    public String getKeyPublic() {
        return keyPublic;
    }

    public void setKeyPublic(String keyPublic) {
        this.keyPublic = keyPublic;
    }

    public String getKeyCert() {
        return keyCert;
    }

    public void setKeyCert(String keyCert) {
        this.keyCert = keyCert;
    }

    public String getKeyCertPwd() {
        return keyCertPwd;
    }

    public void setKeyCertPwd(String keyCertPwd) {
        this.keyCertPwd = keyCertPwd;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public Boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }


    @Override
    public String toString() {
        return "MerchantDetails{" +
                "detailsId=" + detailsId +
                ", payType=" + payType +
                ", appid=" + appid +
                ", mchId=" + mchId +
                ", certStoreType=" + certStoreType +
                ", keyPrivate=" + keyPrivate +
                ", keyPublic=" + keyPublic +
                ", keyCert=" + keyCert +
                ", keyCertPwd=" + keyCertPwd +
                ", notifyUrl=" + notifyUrl +
                ", returnUrl=" + returnUrl +
                ", signType=" + signType +
                ", seller=" + seller +
                ", subAppId=" + subAppId +
                ", subMchId=" + subMchId +
                ", inputCharset=" + inputCharset +
                ", isTest=" + isTest +
                "}";
    }

}
