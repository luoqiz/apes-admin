package top.luoqiz.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * <p>
 * 支付订单记录表
 * </p>
 *
 * @author luoqiz
 * @since 2021-04-16
 */
@TableName("pay_order")
@Schema(name = "PayOrder对象", description = "支付订单记录表")
public class PayOrderEntity extends Model<PayOrderEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("id")
    private Long id;

    @Schema(description = "订单标题")
    private String title;

    @Schema(description = "订单描述")
    private String description;

    @Schema(description = "支付渠道：aliPay 支付宝， wxPay微信..等等")
    private String payType;

    @Schema(description = "下单方式：app wap page 等")
    private String payWay;

    @Schema(description = "支付金额")
    private Double payAmt;

    @Schema(description = "总优惠金额")
    private Double discountAmt;

    @Schema(description = "红包优惠")
    private Double redpackmal;

    @Schema(description = "备注")
    private String mark;

    @Schema(description = "过期时间")
    private LocalDateTime expiredTime;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(Double payAmt) {
        this.payAmt = payAmt;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public Double getRedpackmal() {
        return redpackmal;
    }

    public void setRedpackmal(Double redpackmal) {
        this.redpackmal = redpackmal;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
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
        return "PayOrder{" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", payType=" + payType +
                ", payWay=" + payWay +
                ", payAmt=" + payAmt +
                ", discountAmt=" + discountAmt +
                ", redpackmal=" + redpackmal +
                ", mark=" + mark +
                ", expiredTime=" + expiredTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }

}
