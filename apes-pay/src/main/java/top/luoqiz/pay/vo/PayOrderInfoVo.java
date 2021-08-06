package top.luoqiz.pay.vo;

import com.egzosn.pay.ali.bean.AliTransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.web.annotation.In;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 创建订单实体类
 *
 * @author luoqiz
 */
public class PayOrderInfoVo {

    @Schema(description = "支付渠道 1 支付宝 2 微信")
    @NotNull
    @In(values = {"1", "2"})
    private String paymentChannel;

    @Schema(description = "订单标题")
    @NotEmpty
    private String orderTitle;

    @Schema(description = "订单描述")
    @NotEmpty
    private String orderDesc;

    @Schema(description = "交易方式")
    @In(enums = AliTransactionType.class)
    private String wayTrade;

    @Schema(description = "交易金额（单位为分）")
    @Min(1)
    private BigDecimal price;

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getWayTrade() {
        return wayTrade;
    }

    public void setWayTrade(String wayTrade) {
        this.wayTrade = wayTrade;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
