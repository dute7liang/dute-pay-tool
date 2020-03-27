package com.dute7liang.pay.tool.vx.core;

import com.alibaba.fastjson.JSON;
import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.core.common.AbstractWxPay;
import com.dute7liang.pay.tool.common.bean.TradeToken;
import com.dute7liang.pay.tool.common.http.client.HttpClient;
import com.dute7liang.pay.tool.common.http.response.SimpleResponse;
import com.dute7liang.pay.tool.vx.bean.WxTrade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信手机网页支付
 * @Auther: yangyuan
 * @Date: 2019/1/8 18:00
 */
public class WxWebMobilePay extends AbstractWxPay {

    private static Log log = LogFactory.getLog(WxWebMobilePay.class);

    @Override
    public TradeToken<String> pay(WxTrade trade) {
        try{
            String link = prepay(trade);

            return new TradeToken<String>() {
                @Override
                public String value() {
                    return link;
                }
            };
        }catch(Exception e){
            log.error("[微信支付]发起微信支付异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 预支付
     * @param trade
     * @return
     * @throws DocumentException
     */
    private String prepay(WxTrade trade) throws DocumentException {
        /**
         * 从缓存中寻找二维码链接
         */

        /**
         * 远程请求二维码链接
         */
        log.info("[微信支付]开始预支付");

        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", trade.getAppid());
        params.put("mch_id", trade.getMchid());
        params.put("nonce_str", trade.getNonceStr());
        params.put("body", trade.getBody());
        params.put("detail", trade.getDetail());
        params.put("out_trade_no", trade.getOutTradeNo());
        params.put("total_fee", trade.getTotalFee());
        params.put("spbill_create_ip", trade.getSpbillCreateIp());
        params.put("notify_url", trade.getNotifyUrl());
        params.put("trade_type", trade.getTradeType());
        params.put("scene_info", trade.getSceneInfo());
        params.put("sign", signMD5(params));

        log.info("[微信支付]预支付参数构造完成\n" + JSON.toJSONString(params));

        Document paramsDoc = buildDocFromMap(params);

        log.info("[微信支付]预支付XML参数构造完成\n" + paramsDoc.asXML());

        log.info("[微信支付]开始请求微信服务器进行预支付");

        SimpleResponse response = HttpClient.getClient().post(WxPayConfig.getUnifiedorderURL(), paramsDoc.asXML());
        if(response.getCode() != 200){
            throw new RuntimeException("请求预支付通信失败, HTTP STATUS[" + response.getCode() + "]");
        }
        String responseBody = response.getStringBody();

        log.info("[微信支付]预支付通信成功\n" + responseBody);

        /**
         * 解析响应数据
         */
        Document responseDoc = DocumentHelper.parseText(responseBody);
        Element mwebUrlElement = responseDoc.getRootElement().element("mweb_url");
        if(mwebUrlElement == null){
            throw new RuntimeException("请求预支付未找到付款链接(mweb_url)");
        }
        String mwebUrl = mwebUrlElement.getTextTrim();

        log.info("[微信支付]成功获取付款链接[" + mwebUrl + "]");

        /**
         * 缓存二维码链接
         */

        return mwebUrl;
    }
}