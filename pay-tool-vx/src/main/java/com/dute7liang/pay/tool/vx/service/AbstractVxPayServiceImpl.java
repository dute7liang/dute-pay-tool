package com.dute7liang.pay.tool.vx.service;

import com.dute7liang.pay.tool.common.http.client.HttpClient;
import com.dute7liang.pay.tool.common.http.response.SimpleResponse;
import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.constant.WxConstant;
import com.dute7liang.pay.tool.vx.core.notify.WxPayOrderNotifyResult;
import com.dute7liang.pay.tool.vx.core.notify.WxPayRefundNotifyResult;
import com.dute7liang.pay.tool.vx.core.result.*;
import com.dute7liang.pay.tool.vx.core.trade.*;
import com.dute7liang.pay.tool.vx.exception.WxPayException;
import com.dute7liang.pay.tool.vx.http.HttpsWxClient;
import com.dute7liang.pay.tool.vx.util.SignUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/27 19:09
 */
public class AbstractVxPayServiceImpl implements WxPayServiceI {

    @Setter
    @Getter
    protected WxPayConfig wxPayConfig;

    @Override
    public WxPayConfig getConfig() {
        return wxPayConfig;
    }

    @Override
    public void setConfig(WxPayConfig config) {
        this.wxPayConfig = config;
    }

    protected Logger getLog() {
        return LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public <T> T unifiedorder(WxUnifiedOrderTrade trade) {
        // 组织、检查、签名 参数
        trade.checkAndSign(wxPayConfig);
        // 发送请求
        String url = wxPayConfig.getPayBaseUrl() + "/pay/unifiedorder";
        String responseBody = post(url, trade.toXML(), false);
        // 解析响应数据
        WxPayUnifiedOrderResult result = BaseWxPayResult.fromXML(responseBody, WxPayUnifiedOrderResult.class);
        result.checkResult(this,trade.getSignType());
        String prepayId = result.getPrepayId();
        if (StringUtils.isBlank(prepayId)) {
            throw new WxPayException(String.format("无法获取prepay id，错误代码： '%s'，信息：%s。",
                    result.getErrCode(), result.getErrCodeDes()));
        }
        // 根据情况返回数据
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        switch (trade.getTradeType()) {
            case WxConstant.TradeType.MWEB: {
                return (T) new WxPayMwebOrderResult(result.getMwebUrl());
            }
            case WxConstant.TradeType.NATIVE: {
                return (T) new WxPayNativeOrderResult(result.getCodeURL());
            }
            case WxConstant.TradeType.APP: {
                // APP支付绑定的是微信开放平台上的账号，APPID为开放平台上绑定APP后发放的参数
                String appId = result.getAppid();
                Map<String, String> configMap = new HashMap<>();
                String partnerId = result.getMchId();
                configMap.put("appid", appId);
                configMap.put("prepayid", prepayId);
                configMap.put("partnerid", partnerId);
                String packageValue = "Sign=WXPay";
                configMap.put("package", packageValue);
                configMap.put("timestamp", timestamp);
                configMap.put("noncestr", trade.getNonceStr());

                final WxPayAppOrderResult vo = WxPayAppOrderResult.builder()
                        .appId(appId)
                        .prepayId(prepayId)
                        .partnerId(partnerId)
                        .packageValue(packageValue)
                        .timeStamp(timestamp)
                        .nonceStr(trade.getNonceStr())
                        .sign(SignUtils.createSign(configMap, trade.getSignType(), this.getConfig().getMchKey(), null))
                        .build();
                return (T) vo;
            }
            case WxConstant.TradeType.JSAPI: {
                String appId = trade.getAppid();
                Map<String, String> configMap = new HashMap<>();
                configMap.put("appid", appId);
                configMap.put("timestamp", timestamp);
                configMap.put("noncestr", trade.getNonceStr());
                configMap.put("package", "prepay_id=" + prepayId);
                configMap.put("signType", trade.getSignType());
                WxPayMpOrderResult payResult = WxPayMpOrderResult.builder()
                        .appId(appId)
                        .timeStamp(timestamp)
                        .nonceStr(trade.getNonceStr())
                        .packageValue("prepay_id=" + prepayId)
                        .signType(trade.getNonceStr())
                        .build();
                payResult.setPaySign(SignUtils.createSign(configMap, trade.getSignType(), this.getConfig().getMchKey(), null));
                return (T) payResult;
            }
            default: {
                throw new WxPayException("该交易类型暂不支持");
            }
        }
    }

    @Override
    public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) {
        WxPayOrderQueryTrade trade = new WxPayOrderQueryTrade();
        trade.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
        trade.setTransactionId(StringUtils.trimToNull(transactionId));

        trade.checkAndSign(this.getConfig());
        String url = this.getConfig().getPayBaseUrl() + "/pay/orderquery";
        String responseContent = this.post(url, trade.toXML(), false);

        WxPayOrderQueryResult result = BaseWxPayResult.fromXML(responseContent, WxPayOrderQueryResult.class);
        result.composeCoupons();
        result.checkResult(this,trade.getSignType());
        return result;
    }

    @Override
    public WxPayOrderCloseResult closeOrder(String outTradeNo) {
        if (StringUtils.isBlank(outTradeNo)) {
            throw new WxPayException("out_trade_no不能为空");
        }

        WxPayOrderCloseTrade trade = new WxPayOrderCloseTrade();
        trade.setOutTradeNo(StringUtils.trimToNull(outTradeNo));

        trade.checkAndSign(this.getConfig());

        String url = this.getConfig().getPayBaseUrl() + "/pay/closeorder";
        String responseContent = this.post(url, trade.toXML(), false);
        WxPayOrderCloseResult result = BaseWxPayResult.fromXML(responseContent, WxPayOrderCloseResult.class);
        result.checkResult(this,trade.getSignType());
        return result;
    }

    @Override
    public WxPayRefundResult refund(WxPayRefundTrade trade) {
        trade.checkAndSign(this.getConfig());

        String url = this.getConfig().getPayBaseUrl() + "/secapi/pay/refund";
        if (this.getConfig().isUseSandboxEnv()) {
            url = this.getConfig().getPayBaseUrl() + "/sandboxnew/pay/refund";
        }

        String responseContent = this.post(url, trade.toXML(), true);
        WxPayRefundResult result = BaseWxPayResult.fromXML(responseContent, WxPayRefundResult.class);
        result.composeRefundCoupons();
        result.checkResult(this,trade.getSignType());
        return result;
    }

    @Override
    public WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId) {
        WxPayRefundQueryTrade trade = new WxPayRefundQueryTrade();
        trade.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
        trade.setTransactionId(StringUtils.trimToNull(transactionId));
        trade.setOutRefundNo(StringUtils.trimToNull(outRefundNo));
        trade.setRefundId(StringUtils.trimToNull(refundId));

        trade.checkAndSign(this.getConfig());

        String url = this.getConfig().getPayBaseUrl() + "/pay/refundquery";
        String responseContent = this.post(url, trade.toXML(), false);
        WxPayRefundQueryResult result = BaseWxPayResult.fromXML(responseContent, WxPayRefundQueryResult.class);
        result.composeRefundRecords();
        result.checkResult(this,trade.getSignType());
        return result;
    }

    @Override
    public WxPayOrderNotifyResult parseOrderNotifyResult(String xmlData) throws WxPayException {
        try {
            getLog().debug("微信支付异步通知请求参数：{}", xmlData);
            WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlData,WxPayOrderNotifyResult.class);
            getLog().debug("微信支付异步通知请求解析后的对象：{}", result);
            result.checkResult(this, result.getSignType());
            return result;
        } catch (WxPayException e) {
            throw e;
        } catch (Exception e) {
            throw new WxPayException("发生异常！", e);
        }
    }

    @Override
    public WxPayRefundNotifyResult parseRefundNotifyResult(String xmlData) throws WxPayException {
        try {
            getLog().debug("微信支付退款异步通知参数：{}", xmlData);
            WxPayRefundNotifyResult result = BaseWxPayResult.fromXML(xmlData, WxPayRefundNotifyResult.class);
            result.decryptReqInfo(this.getConfig().getMchKey());
            getLog().debug("微信支付退款异步通知解析后的对象：{}", result);
            return result;
        } catch (Exception e) {
            throw new WxPayException("发生异常，" + e.getMessage(), e);
        }
    }


    public String post(String url,String requestStr,boolean userKey){
        SimpleResponse response;
        if(userKey){
            response = HttpsWxClient.getInstance(this.wxPayConfig).post(url, requestStr);
        }else{
            response = HttpClient.getClient().post(url, requestStr);
        }
        if(response.getCode() != 200){
            throw new RuntimeException("请求预支付通信失败, HTTP STATUS[" + response.getCode() + "]");
        }
        String responseBody = response.getStringBody();
        getLog().debug("[微信支付]预支付通信成功\n" + responseBody);
        if (StringUtils.isBlank(responseBody)) {
            throw new WxPayException("无响应结果");
        }
        return responseBody;
    }
}
