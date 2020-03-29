package com.dute7liang.pay.tool.vx.manager;

import com.dute7liang.pay.tool.common.bean.TradeStatus;
import com.dute7liang.pay.tool.common.bean.TradeToken;
import com.dute7liang.pay.tool.common.exception.RefundException;
import com.dute7liang.pay.tool.common.http.client.HttpClient;
import com.dute7liang.pay.tool.common.http.response.SimpleResponse;
import com.dute7liang.pay.tool.vx.bean.WxTrade;
import com.dute7liang.pay.tool.vx.bean.WxTradeTest;
import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.config.WxPayConfigTest;
import com.dute7liang.pay.tool.vx.core.*;
import com.dute7liang.pay.tool.vx.core.result.BaseWxPayResult;
import com.dute7liang.pay.tool.vx.core.result.WxPayUnifiedOrderResult;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;

import java.util.HashMap;
import java.util.Map;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/27 19:09
 */
@Slf4j
public class AbstractVxPayServiceImpl implements WxPayServiceI {

    @Getter
    @Setter
    protected WxPayConfigTest wxPayConfig;


    public <T> TradeToken<T> mobilePay(WxTradeTest trade) throws DocumentException {
        trade.checkAndSign(wxPayConfig);

        SimpleResponse response = HttpClient.getClient().post(WxPayConfig.getUnifiedorderURL(), trade.toXML());
        if(response.getCode() != 200){
            throw new RuntimeException("请求预支付通信失败, HTTP STATUS[" + response.getCode() + "]");
        }
        String responseBody = response.getStringBody();

        log.debug("[微信支付]预支付通信成功\n" + responseBody);

        /**
         * 解析响应数据
         */
        WxPayUnifiedOrderResult result = BaseWxPayResult.fromXML(responseBody, WxPayUnifiedOrderResult.class);
        String prepayId = result.getPrepayId();

        Map<String, String> params = new HashMap<>();
        params.put("appid", trade.getAppid());
        params.put("partnerid", trade.getMchId());
        params.put("prepayid", prepayId);
        params.put("package", "Sign=WXPay");
        params.put("noncestr",  trade.getNonceStr());
        params.put("timestamp",  String.valueOf(System.currentTimeMillis()/1000));
        params.put("sign",  signMD5(params));
        return () -> params;
    }


    /**
     * 支付业务核心
     */
    protected static final WxMobilePay wxMobilePay = new WxMobilePay();
    protected static final WxWebPcPay wxWebPcPay = new WxWebPcPay();
    protected static final WxWebMobilePay wxWebMobilePay = new WxWebMobilePay();
    protected static final WxQrcodePay wxQrcodePay = new WxQrcodePay();
    protected static final WxJsSdkPay wxJsSdkPay = new WxJsSdkPay();

    @Override
    public <T> TradeToken<T>  mobilePay(WxTrade trade) {
        return (TradeToken<T>) wxMobilePay.pay(trade);
    }

    @Override
    public <T> TradeToken<T> webPcPay(WxTrade trade) {
        return (TradeToken<T>) wxWebPcPay.pay(trade);
    }

    @Override
    public <T> TradeToken<T> webMobilePay(WxTrade trade) {
        return (TradeToken<T>) wxWebMobilePay.pay(trade);
    }

    @Override
    public <T> TradeToken<T> qrcodePay(WxTrade trade) {
        return (TradeToken<T>) wxQrcodePay.pay(trade);
    }

    @Override
    public <T> TradeToken<T> jsSdkPay(WxTrade trade) {
        return (TradeToken<T>) wxJsSdkPay.pay(trade);
    }

    @Override
    public void refund(WxTrade trade) throws RefundException {
        wxMobilePay.refund(trade);
    }

    @Override
    public TradeStatus status(WxTrade trade) {
        return wxMobilePay.status(trade);
    }
}
