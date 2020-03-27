package com.dute7liang.pay.tool.demo.vx;

import com.dute7liang.pay.tool.common.bean.Trade;
import com.dute7liang.pay.tool.vx.bean.WxTrade;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/19 21:57
 */
public class VxPayDemo {

    public void wxPay(){
        Trade trade = WxTrade
                .webMobilePay()
                .body("商品标题")
                .outTradeNo("订单号")
                .totalFee("1")
                .spbillCreateIp("127.0.0.1")
                .sceneInfo("商品测试场景")
                .build();
//        TradeToken<String> token = goodsTradeManager.webMobilePay(trade);
//        String url = token.value();
    }

}
