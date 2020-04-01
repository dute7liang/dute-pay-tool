package com.dute7liang.pay.tool.demo.vx;


import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.constant.WxConstant;
import com.dute7liang.pay.tool.vx.core.notify.WxPayOrderNotifyResult;
import com.dute7liang.pay.tool.vx.core.result.*;
import com.dute7liang.pay.tool.vx.core.trade.WxUnifiedOrderTrade;
import com.dute7liang.pay.tool.vx.service.WxPayServiceI;
import com.dute7liang.pay.tool.vx.service.WxPayServiceImpl;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/19 21:57
 */
public class VxPayDemo {

    public static void main(String[] args) {
//        wxPay();
//        queryOrder();
//        closeOrder();
//        refund();
//        refundQuery();
        parseOrderNotifyResult();
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

    /**
     * V202009315678215623
     * V202009315678215642
     */
    public static void wxPay() {
        WxPayServiceI wxPayService = init();

        WxUnifiedOrderTrade trade = WxUnifiedOrderTrade.newBuilder()
                .tradeType(WxConstant.TradeType.APP)
                .body("主题")
                .outTradeNo("V202009315678215642")
                .totalFee(100)
                .spbillCreateIp("192.168.72.36").build();

        Object unifiedorder = wxPayService.unifiedorder(trade);
    }


    public static void closeOrder(){
        String response = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[BFK89FC6rxKCOjLX]]></nonce_str>\n" +
                "   <sign><![CDATA[72B321D92A7BFA0B2509F3D13C7B1631]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <result_msg><![CDATA[OK]]></result_msg>\n" +
                "</xml>";
        WxPayOrderCloseResult result = BaseWxPayResult.fromXML(response, WxPayOrderCloseResult.class);
        result.checkResult(init(),"MD5");
        System.out.println(22);
    }

    public static void refund(){
        String response = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[NfsMFbUFpdbEhPXP]]></nonce_str>\n" +
                "   <sign><![CDATA[B7274EB9F8925EB93100DD2085FA56C0]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
                "   <out_refund_no><![CDATA[1415701182]]></out_refund_no>\n" +
                "   <refund_id><![CDATA[2008450740201411110000174436]]></refund_id>\n" +
                "   <refund_fee>1</refund_fee>\n" +
                "   <coupon_refund_count>4</coupon_refund_count>\n" +
                "   <coupon_type_1>1</coupon_type_1>\n" +
                "   <coupon_type_2>1</coupon_type_2>\n" +
                "   <coupon_type_3>1</coupon_type_3>\n" +
                "   <coupon_type_0>1</coupon_type_0>\n" +
                "</xml>";
        WxPayRefundResult result = BaseWxPayResult.fromXML(response, WxPayRefundResult.class);
        result.composeRefundCoupons();
        result.checkResult(init(),"MD5");
    }


    public static void refundQuery(){
        String response = "<xml>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[TeqClE3i0mvn3DrK]]></nonce_str>\n" +
                "   <out_refund_no_0><![CDATA[1415701182]]></out_refund_no_0>\n" +
                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
                "   <refund_count>1</refund_count>\n" +
                "   <refund_fee_0>1</refund_fee_0>\n" +
                "   <refund_id_0><![CDATA[2008450740201411110000174436]]></refund_id_0>\n" +
                "   <refund_status_0><![CDATA[PROCESSING]]></refund_status_0>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <sign><![CDATA[1F2841558E233C33ABA71A961D27561C]]></sign>\n" +
                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
                "</xml>";
        WxPayRefundQueryResult result = BaseWxPayResult.fromXML(response, WxPayRefundQueryResult.class);
        result.composeRefundRecords();
        result.checkResult(init(),"MD5");
    }

    public static void parseOrderNotifyResult(){
        String response = "<xml>\n" +
                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "  <attach><![CDATA[支付测试]]></attach>\n" +
                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
                "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
                "  <total_fee>1</total_fee>\n" +
                "<coupon_fee_0><![CDATA[10]]></coupon_fee_0>\n" +
                "<coupon_count><![CDATA[1]]></coupon_count>\n" +
                "<coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
                "<coupon_id_0><![CDATA[10000]]></coupon_id_0>\n" +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                "</xml>";

        WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(response,WxPayOrderNotifyResult.class);
        result.checkResult(init(),result.getSignType());
    }

}
