package com.dute7liang.pay.tool.demo.web;

import com.dute7liang.pay.tool.demo.response.Result;
import com.dute7liang.pay.tool.vx.constant.WxConstant;
import com.dute7liang.pay.tool.vx.core.notify.WxPayNotifyResponse;
import com.dute7liang.pay.tool.vx.core.notify.WxPayOrderNotifyResult;
import com.dute7liang.pay.tool.vx.core.notify.WxPayRefundNotifyResult;
import com.dute7liang.pay.tool.vx.core.trade.WxPayRefundTrade;
import com.dute7liang.pay.tool.vx.core.trade.WxUnifiedOrderTrade;
import com.dute7liang.pay.tool.vx.service.WxPayServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信demo
 * @author: zl
 * @Date: 2020-4-1 13:50
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private WxPayServiceI wxPayService;

    /**
     * 统一下单
     * 创建订单
     */
    @GetMapping("unifiedorder")
    public Result unifiedorder(){
        WxUnifiedOrderTrade trade = WxUnifiedOrderTrade.newBuilder()
                .tradeType(WxConstant.TradeType.APP)
                .body("主题")
                .outTradeNo("V202009315678215642")
                .totalFee(100)
                .spbillCreateIp("192.168.72.36").build();

        return Result.success(wxPayService.unifiedorder(trade));
    }

    /**
     * 订单的异步通知，需要保证其幂等性
     */
    @GetMapping("orderNotify")
    public String parseOrderNotifyResult(@RequestBody String xmlData){
        try {
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
            // 里面已经对签名做个验证

            Integer totalFee = notifyResult.getTotalFee(); // 支付的金额，需要去判断一下 支付的金额和系统的金额对不对防止攻击代码
            String outTradeNo = notifyResult.getOutTradeNo(); // 拿到订单号去做业务的处理

            return WxPayNotifyResponse.success();
        }catch (Exception e){
            log.error("微信订单异步通知异常", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    /**
     * 订单查询
     * @return
     */
    @GetMapping("queryOrder")
    public Result queryOrder(){
        return Result.success(wxPayService.queryOrder(null,"V202009315678215642"));
    }

    /**
     * 关闭订单
     * @return
     */
    @GetMapping("closeOrder")
    public Result closeOrder(){
        return Result.success(wxPayService.closeOrder("V202009315678215642"));
    }

    /**
     * 退款
     * @return
     */
    @GetMapping("refund")
    public Result refund(){
        WxPayRefundTrade trade = WxPayRefundTrade.newBuilder()
                .outTradeNo("V202009315678215642").build();
        return Result.success(wxPayService.refund(trade));
    }

    /**
     * 退款订单回调
     * @param xmlData
     * @return
     */
    @GetMapping("refundNotify")
    public String parseRefundNotifyResult(@RequestBody String xmlData){
        try {
            WxPayRefundNotifyResult notifyResult = wxPayService.parseRefundNotifyResult(xmlData);
            // 里面已经对签名做个验证

            String refundStatus = notifyResult.getReqInfo().getRefundStatus(); // 退款状态
            if(WxConstant.RefundStatus.SUCCESS.equals(refundStatus)){ // 退款成功
                String outTradeNo = notifyResult.getReqInfo().getOutRefundNo(); // 拿到订单号去做业务的处理
            }

            return WxPayNotifyResponse.success();
        }catch (Exception e){
            log.error("微信订单异步通知异常", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    /**
     * 退款订单的查询
     * @return
     */
    @GetMapping("refundQuery")
    public Result refundQuery(){
        return Result.success(wxPayService.refundQuery(null,null,"V202009315678215642",null));
    }




}
