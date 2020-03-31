package com.dute7liang.pay.tool.demo.vx;

import com.dute7liang.pay.tool.vx.constant.WxConstant;
import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.core.result.WxPayOrderQueryResult;
import com.dute7liang.pay.tool.vx.service.WxPayServiceI;
import com.dute7liang.pay.tool.vx.service.WxPayServiceImpl;
import com.dute7liang.pay.tool.vx.core.trade.WxUnifiedOrderTrade;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/19 21:57
 */
public class VxPayDemo {

    public static void main(String[] args) {
//        wxPay();
        queryOrder();
    }

    public static WxPayServiceI init(){
        WxPayConfig config = new WxPayConfig();
        config.setAppid("wx8b5873b12a0d15cb");
        config.setMchId("1567555201");
        config.setMchKey("jianqingmaiwangluokejiyxgs591591");
        config.setNotifyURL("http://dute7liang.com/pay/wxNotify");
//        config.setCertPath();
//        config.setCertPassword();

        WxPayServiceI wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(config);
        return wxPayService;
    }

    public static void queryOrder(){
        WxPayServiceI wxPayService = init();
        WxPayOrderQueryResult result = wxPayService.queryOrder(null, "V202009315678215623");
        System.out.println(result);
    }

    public static void wxPay() {
        WxPayServiceI wxPayService = init();

        WxUnifiedOrderTrade trade = WxUnifiedOrderTrade.newBuilder()
                .tradeType(WxConstant.TradeType.APP)
                .body("主题")
                .outTradeNo("V202009315678215623")
                .totalFee(100)
                .spbillCreateIp("192.168.72.36").build();

        Object unifiedorder = wxPayService.unifiedorder(trade);



    }
}
