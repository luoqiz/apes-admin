package top.luoqiz.pay.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.pay.entity.PayOrderEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
public class PayOrderVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long id;


    @Schema(description = "订单标题")
    @ExcelProperty(value = "订单标题", index = 1)
    private String title;


    @Schema(description = "订单描述")
    @ExcelProperty(value = "订单描述", index = 2)
    private String description;


    @Schema(description = "支付渠道：aliPay 支付宝， wxPay微信..等等")
    @ExcelProperty(value = "支付渠道：aliPay 支付宝， wxPay微信..等等", index = 3)
    private String payType;


    @Schema(description = "下单方式：app wap page 等")
    @ExcelProperty(value = "下单方式：app wap page 等", index = 4)
    private String payWay;


    @Schema(description = "支付金额")
    @ExcelProperty(value = "支付金额", index = 5)
    private Double payAmt;


    @Schema(description = "总优惠金额")
    @ExcelProperty(value = "总优惠金额", index = 6)
    private Double discountAmt;


    @Schema(description = "红包优惠")
    @ExcelProperty(value = "红包优惠", index = 7)
    private Double redpackmal;


    @Schema(description = "备注")
    @ExcelProperty(value = "备注", index = 8)
    private String mark;


    @Schema(description = "过期时间")
    @ExcelProperty(value = "过期时间", index = 9)
    private LocalDateTime expiredTime;


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


    public static PayOrderVo fromEntity(PayOrderEntity entity) {
        if (entity == null) {
            return null;
        }
        PayOrderVo vo = new PayOrderVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<PayOrderVo> fromEntity(List<PayOrderEntity> entities) {
        LinkedList<PayOrderVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (PayOrderEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
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
