package top.luoqiz.pay.controller;

import com.egzosn.pay.common.api.PayMessageInterceptor;
import com.egzosn.pay.spring.boot.core.PayServiceManager;
import com.egzosn.pay.spring.boot.core.bean.MerchantPayOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.luoqiz.common.web.annotation.AnonymousAccess;
import top.luoqiz.common.web.annotation.IgnoreWrapper;
import top.luoqiz.pay.vo.PayOrderInfoVo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * @author egan
 * email egzosn@gmail.com
 * date 2019/5/26.20:10
 */
@Tag(name = "支付相关接口")
@RequestMapping("pay")
@Controller
public class PayMerchantController {

    @Autowired
    private PayServiceManager manager;

    /**
     * 手机H5网页支付
     *
     * @return 网页
     */
    @AnonymousAccess
    @IgnoreWrapper
    @Operation(summary = "手机H5网页支付")
    @ResponseBody
    @GetMapping(value = "toPay.html", produces = "text/html;charset=UTF-8")
    public String toPay(@Validated PayOrderInfoVo payOrderInfo) {
        if ("1".equals(payOrderInfo.getPaymentChannel())) {
            payOrderInfo.setPrice(payOrderInfo.getPrice().divide(new BigDecimal(100)));
        }
        MerchantPayOrder payOrder =
                new MerchantPayOrder(payOrderInfo.getPaymentChannel(), payOrderInfo.getWayTrade(), payOrderInfo.getOrderTitle(), payOrderInfo.getOrderDesc()
                        , payOrderInfo.getPrice(), UUID.randomUUID().toString().replace("-", ""));
        return manager.toPay(payOrder);
    }

    /**
     * 手机app页支付
     *
     * @return App支付信息
     */
    @Operation(summary = "手机app支付")
    @ResponseBody
    @GetMapping(value = "app")
    public Map<String, Object> toAppPay(@Validated PayOrderInfoVo payOrderInfo) {
        if ("1".equals(payOrderInfo.getPaymentChannel())) {
            payOrderInfo.setPrice(payOrderInfo.getPrice().divide(new BigDecimal(100)));
        }
        MerchantPayOrder payOrder =
                new MerchantPayOrder(payOrderInfo.getPaymentChannel(), payOrderInfo.getWayTrade(), payOrderInfo.getOrderTitle(), payOrderInfo.getOrderDesc()
                        , payOrderInfo.getPrice(), UUID.randomUUID().toString().replace("-", ""));
        return manager.app(payOrder);
    }

    /**
     * 二维码
     *
     * @return 二维码
     */
    @Operation(summary = "二维码")
    @ResponseBody
    @GetMapping(value = "toQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
    public byte[] toQrPay(@Validated PayOrderInfoVo payOrderInfo) throws IOException {
        if ("1".equals(payOrderInfo.getPaymentChannel())) {
            payOrderInfo.setPrice(payOrderInfo.getPrice().divide(new BigDecimal(100)));
        }
        MerchantPayOrder payOrder =
                new MerchantPayOrder(payOrderInfo.getPaymentChannel(), payOrderInfo.getWayTrade(), payOrderInfo.getOrderTitle(), payOrderInfo.getOrderDesc()
                        , payOrderInfo.getPrice(), UUID.randomUUID().toString().replace("-", ""));
        return manager.toQrPay(payOrder);
    }

    /**
     * 二维码信息
     *
     * @param detailsId 列表id
     * @param wayTrade  交易方式
     * @return 二维码信息
     */
    @ResponseBody
    @RequestMapping(value = "getQrPay.json")
    public String getQrPay(String detailsId, String wayTrade, BigDecimal price) throws IOException {
        MerchantPayOrder payOrder = new MerchantPayOrder(detailsId, wayTrade, "订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""));
        return manager.getQrPay(payOrder);
    }


    /**
     * 支付回调地址
     *
     * @param request   请求
     * @param detailsId 列表id
     * @return 支付是否成功
     * @throws IOException IOException
     *                     拦截器相关增加， 详情查看{@link com.egzosn.pay.common.api.PayService#addPayMessageInterceptor(PayMessageInterceptor)}
     *                     <p>
     *                     业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看{@link com.egzosn.pay.common.api.PayService#setPayMessageHandler(com.egzosn.pay.common.api.PayMessageHandler)}
     *                     </p>
     *                     如果未设置 {@link com.egzosn.pay.common.api.PayMessageHandler} 那么会使用默认的 {@link com.egzosn.pay.common.api.DefaultPayMessageHandler}
     */
    @AnonymousAccess
    @RequestMapping(value = "payBack{detailsId}.json")
    public String payBack(HttpServletRequest request, @PathVariable String detailsId) throws IOException {
        //业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看com.egzosn.pay.common.api.PayService.setPayMessageHandler()
        return manager.payBack(detailsId, request.getParameterMap(), request.getInputStream());
    }

}
