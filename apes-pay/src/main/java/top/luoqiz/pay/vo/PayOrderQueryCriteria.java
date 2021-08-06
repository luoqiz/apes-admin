package top.luoqiz.pay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 支付订单记录表 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-16
 */
public class PayOrderQueryCriteria extends BasePageCriteria {

    @Schema(description = "订单标题")
    private String title;

    @Schema(description = "支付渠道：aliPay 支付宝， wxPay微信..等等")
    private String payType;

    @Schema(description = "下单方式：app wap page 等")
    private String payWay;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }


    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }


}