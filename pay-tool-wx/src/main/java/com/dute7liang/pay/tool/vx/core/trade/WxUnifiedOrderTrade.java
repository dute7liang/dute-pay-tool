package com.dute7liang.pay.tool.vx.core.trade;

import com.dute7liang.pay.tool.common.bean.Trade;
import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.constant.WxConstant;
import com.dute7liang.pay.tool.vx.exception.WxPayException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 统一下单接口的订单数据
 * <br/>
 * author: zl
 * Date: 2020/3/27 19:44
 */
@Getter
@Setter
@Builder(builderMethodName = "newBuilder")
public class WxUnifiedOrderTrade extends WxBaseTrade implements Trade,Serializable {

    /**
     * <pre>
     * 字段名：接口版本号.
     * 变量名：version
     * 是否必填：单品优惠必填
     * 类型：String(32)
     * 示例值：1.0
     * 描述：单品优惠新增字段，接口版本号，区分原接口，默认填写1.0。
     * 入参新增version后，则支付通知接口也将返回单品优惠信息字段promotion_detail，请确保支付通知的签名验证能通过。
     * 更多信息，详见文档：https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
     * </pre>
     */
    private String version;

    /**
     * <pre>
     * 字段名：设备号.
     * 变量名：device_info
     * 是否必填：否
     * 类型：String(32)
     * 示例值：013467007045764
     * 描述：终端设备号(门店号或收银设备Id)，注意：PC网页或公众号内支付请传"WEB"
     * </pre>
     */
    private String deviceInfo;

    /**
     * <pre>
     * 字段名：商品描述.
     * 变量名：body
     * 是否必填：是
     * 类型：String(128)
     * 示例值： 腾讯充值中心-QQ会员充值
     * 描述：商品简单描述，该字段须严格按照规范传递，具体请见参数规定
     * </pre>
     */
    private String body;

    /**
     * <pre>
     * 字段名：商品详情.
     * 变量名：detail
     * 是否必填：否
     * 类型：String(6000)
     * 示例值： {  "goods_detail":[
     *  {
     * "goods_id":"iphone6s_16G",
     * "wxpay_goods_id":"1001",
     * "goods_name":"iPhone6s 16G",
     * "goods_num":1,
     * "price":528800,
     * "goods_category":"123456",
     * "body":"苹果手机"
     * },
     * {
     * "goods_id":"iphone6s_32G",
     * "wxpay_goods_id":"1002",
     * "goods_name":"iPhone6s 32G",
     * "quantity":1,
     * "price":608800,
     * "goods_category":"123789",
     * "body":"苹果手机"
     * }
     * ]
     * }
     * 描述：商品详细列表，使用Json格式，传输签名前请务必使用CDATA标签将JSON文本串保护起来。
     * goods_detail []：
     * └ goods_id String 必填 32 商品的编号
     * └ wxpay_goods_id String 可选 32 微信支付定义的统一商品编号
     * └ goods_name String 必填 256 商品名称
     * └ goods_num Int 必填 商品数量
     * └ price Int 必填 商品单价，单位为分
     * └ goods_category String 可选 32 商品类目Id
     * └ body String 可选 1000 商品描述信息
     * </pre>
     */
    private String detail;

    /**
     * <pre>
     * 字段名：附加数据.
     * 变量名：attach
     * 是否必填：否
     * 类型：String(127)
     * 示例值： 深圳分店
     * 描述：  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     * </pre>
     */
    private String attach;

    /**
     * <pre>
     * 字段名：商户订单号.
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：String(32)
     * 示例值：20150806125346
     * 描述：商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
     * </pre>
     */
    private String outTradeNo;

    /**
     * <pre>
     * 字段名：货币类型.
     * 变量名：fee_type
     * 是否必填：否
     * 类型：String(16)
     * 示例值：CNY
     * 描述： 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * </pre>
     */
    private String feeType;

    /**
     * <pre>
     * 字段名：总金额.
     * 变量名：total_fee
     * 是否必填：是
     * 类型：Int
     * 示例值： 888
     * 描述：订单总金额，单位为分，详见支付金额
     * </pre>
     */
    private Integer totalFee;

    /**
     * <pre>
     * 字段名：终端IP.
     * 变量名：spbill_create_ip
     * 是否必填：是
     * 类型：String(16)
     * 示例值：123.12.12.123
     * 描述：APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
     * </pre>
     */
    private String spbillCreateIp;

    /**
     * <pre>
     * 字段名：交易起始时间.
     * 变量名：time_start
     * 是否必填：否
     * 类型：String(14)
     * 示例值：20091225091010
     * 描述：订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * </pre>
     */
    private String timeStart;

    /**
     * <pre>
     * 字段名：交易结束时间.
     * 变量名：time_expire
     * 是否必填：否
     * 类型：String(14)
     * 示例值：20091227091010
     * 描述：订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
     *   注意：最短失效时间间隔必须大于5分钟
     * </pre>
     */
    private String timeExpire;

    /**
     * <pre>
     * 字段名：商品标记.
     * 变量名：goods_tag
     * 是否必填：否
     * 类型：String(32)
     * 示例值：WXG
     * 描述：商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
     * </pre>
     */
    private String goodsTag;

    /**
     * <pre>
     * 字段名：通知地址.
     * 变量名：notify_url
     * 是否必填：是
     * 类型：String(256)
     * 示例值：http://www.weixin.qq.com/wxpay/pay.php
     * 描述：接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     * </pre>
     */
    private String notifyUrl;

    /**
     * <pre>
     * 字段名：交易类型.
     * 变量名：trade_type
     * 是否必填：是
     * 类型：String(16)
     * 示例值： JSAPI
     * 描述： 取值如下：JSAPI，NATIVE，APP，详细说明见参数规定:
     * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
     * </pre>
     */
    private String tradeType;

    /**
     * <pre>
     * 字段名：商品Id.
     * 变量名：product_id
     * 是否必填：否
     * 类型：String(32)
     * 示例值：12235413214070356458058
     * 描述：trade_type=NATIVE，此参数必传。此id为二维码中包含的商品Id，商户自行定义。
     * </pre>
     */
    private String productId;

    /**
     * <pre>
     * 字段名：指定支付方式.
     * 变量名：limit_pay
     * 是否必填：否
     * 类型：String(32)
     * 示例值：no_credit
     * 描述：no_credit--指定不能使用信用卡支付
     * </pre>
     */
    private String limitPay;

    /**
     * <pre>
     * 字段名：用户标识.
     * 变量名：openid
     * 是否必填：否
     * 类型：String(128)
     * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * 描述：trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
     * openid如何获取，可参考【获取openid】。
     * 企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
     * </pre>
     */
    private String openid;

    /**
     * <pre>
     * 字段名：用户子标识.
     * 变量名：sub_openid
     * 是否必填：否
     * 类型：String(128)
     * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * 描述：trade_type=JSAPI，此参数必传，用户在子商户appid下的唯一标识。
     * openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid。
     * 下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
     * </pre>
     */
//    private String subOpenid;

    /**
     * <pre>
     * 字段名：电子发票入口开放标识.
     * 变量名：	receipt
     * 是否必填：否
     * 类型：String(8)
     * 示例值：Y
     * 描述：Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     * </pre>
     */
    private String receipt;

    /**
     * <pre>
     * 字段名：场景信息.
     * 变量名：scene_info
     * 是否必填：否，对H5支付来说是必填
     * 类型：String(256)
     * 示例值：{
     * "store_id": "SZT10000",
     * "store_name":"腾讯大厦腾大餐厅"
     * }
     * 描述：该字段用于统一下单时上报场景信息，目前支持上报实际门店信息。
     * {
     * "store_id": "", //门店唯一标识，选填，String(32)
     * "store_name":"”//门店名称，选填，String(64)
     * }
     * </pre>
     */
    private String sceneInfo;
    /**
     * <pre>
     * 字段名：浏览器指纹.
     * 变量名：fingerprint
     * 是否必填：否
     * 详细参考 https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=15_7&index=6
     * </pre>
     */
    private String fingerprint;
    /**
     * <pre>
     * 字段名：是否指定服务商分账.
     * 变量名：profit_sharing
     * 是否必填：否
     * 详情：Y-是，需要分账  N-否，不分账，字母要求大写，不传默认不分账
     * 详细参考 https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=24_3&index=3
     * </pre>
     */
    private String profitSharing;


    @Override
    protected void storeMap(Map<String, String> map) {
        map.put("device_info", deviceInfo);
        map.put("body", body);
        map.put("detail", detail);
        map.put("attach", attach);
        map.put("out_trade_no", outTradeNo);
        map.put("fee_type", feeType);
        map.put("total_fee", totalFee.toString());
        map.put("spbill_create_ip", spbillCreateIp);
        map.put("time_start", timeStart);
        map.put("time_expire", timeExpire);
        map.put("goods_tag", goodsTag);
        map.put("notify_url", notifyUrl);
        map.put("trade_type", tradeType);
        map.put("limit_pay", limitPay);
        map.put("receipt", receipt);
    }

    @Override
    public void checkAndSign(WxPayConfig config) {
        if (StringUtils.isBlank(this.getNotifyUrl())) {
            this.setNotifyUrl(config.getNotifyURL());
        }
        super.checkAndSign(config);
    }

    @Override
    protected void checkFields() {
        super.checkFields();
        Set<String> set = new HashSet<>();
        if(StringUtils.isBlank(this.body)){
            set.add("body");
        }
        if(StringUtils.isBlank(this.outTradeNo)){
            set.add("outTradeNo");
        }
        if(this.totalFee == null){
            set.add("totalFee");
        }
        if(StringUtils.isBlank(this.spbillCreateIp)){
            set.add("spbillCreateIp");
        }
        if(StringUtils.isBlank(this.notifyUrl)){
            set.add("notifyUrl");
        }
        if(StringUtils.isBlank(this.tradeType)){
            set.add("tradeType");
        }
        if(set.size() > 0){ // 异常
            throw new WxPayException("必填字段："+StringUtils.join(set,",") + " 需要填写值!");
        }
        if (WxConstant.TradeType.NATIVE.equals(this.getTradeType()) && StringUtils.isBlank(this.getProductId())) {
            throw new WxPayException("当trade_type是'NATIVE'时，需指定非空的product_id值");
        }

    }
}
