package com.dute7liang.pay.tool.vx.core.notify;

import com.dute7liang.pay.tool.vx.constant.WxConstant;
import com.dute7liang.pay.tool.vx.core.result.BaseWxPayResult;
import com.dute7liang.pay.tool.vx.exception.WxPayException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付结果通知.
 * 文档见：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7&index=8
 * https://pay.weixin.qq.com/wiki/doc/api/external/native.php?chapter=9_7
 *
 * @author aimilin6688
 * @since 2.5.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxPayOrderNotifyResult extends BaseWxPayResult {
    private static final long serialVersionUID = 5389718115223345496L;

    /**
     * <pre>
     * 字段名：营销详情.
     * 变量名：promotion_detail
     * 是否必填：否，单品优惠才有
     * 类型：String(6000)
     * 示例值：[{"promotion_detail":[{"promotion_id":"109519","name":"单品惠-6","scope":"SINGLE","type":"DISCOUNT","amount":5,"activity_id":"931386","wxpay_contribute":0,"merchant_contribute":0,"other_contribute":5,"goods_detail":[{"goods_id":"a_goods1","goods_remark":"商品备注","quantity":7,"price":1,"discount_amount":4},{"goods_id":"a_goods2","goods_remark":"商品备注","quantity":1,"price":2,"discount_amount":1}]}]}
     * 描述：单品优惠专用参数，详见https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_203&index=4
     * </pre>
     */
    private String promotionDetail;

    /**
     * <pre>
     * 字段名：设备号.
     * 变量名：device_info
     * 是否必填：否
     * 类型：String(32)
     * 示例值：013467007045764
     * 描述：微信支付分配的终端设备号，
     * </pre>
     */
    private String deviceInfo;

    /**
     * <pre>
     * 字段名：用户标识.
     * 变量名：openid
     * 是否必填：是
     * 类型：String(128)
     * 示例值：wxd930ea5d5a258f4f
     * 描述：用户在商户appid下的唯一标识
     * </pre>
     */
    private String openid;

    /**
     * <pre>
     * 字段名：是否关注公众账号.
     * 变量名：is_subscribe
     * 是否必填：否
     * 类型：String(1)
     * 示例值：Y
     * 描述：用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     * </pre>
     */
    private String isSubscribe;

    /**
     * <pre>
     * 字段名：用户子标识.
     * 变量名：sub_openid
     * 是否必填：是
     * 类型：String(128)
     * 示例值：wxd930ea5d5a258f4f
     * 描述：用户在子商户appid下的唯一标识
     * </pre>
     */
    private String subOpenid;

    /**
     * <pre>
     * 字段名：是否关注子公众账号.
     * 变量名：sub_is_subscribe
     * 是否必填：否
     * 类型：String(1)
     * 示例值：Y
     * 描述：用户是否关注子公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     * </pre>
     */
    private String subIsSubscribe;


    /**
     * <pre>
     * 字段名：交易类型.
     * 变量名：trade_type
     * 是否必填：是
     * 类型：String(16)
     * 示例值：JSAPI
     * JSA描述：PI、NATIVE、APP
     * </pre>
     */
    private String tradeType;

    /**
     * <pre>
     * 字段名：付款银行.
     * 变量名：bank_type
     * 是否必填：是
     * 类型：String(16)
     * 示例值：CMC
     * 描述：银行类型，采用字符串类型的银行标识，银行类型见银行列表
     * </pre>
     */
    private String bankType;

    /**
     * <pre>
     * 字段名：订单金额.
     * 变量名：total_fee
     * 是否必填：是
     * 类型：Int
     * 示例值：100
     * 描述：订单总金额，单位为分
     * </pre>
     */
    private Integer totalFee;
    /**
     * <pre>
     * 字段名：应结订单金额.
     * 变量名：settlement_total_fee
     * 是否必填：否
     * 类型：Int
     * 示例值：100
     * 描述：应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     * </pre>
     */
    private Integer settlementTotalFee;
    /**
     * <pre>
     * 字段名：货币种类.
     * 变量名：fee_type
     * 是否必填：否
     * 类型：String(8)
     * 示例值：CNY
     * 描述：货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * </pre>
     */
    private String feeType;
    /**
     * <pre>
     * 字段名：现金支付金额.
     * 变量名：cash_fee
     * 是否必填：是
     * 类型：Int
     * 示例值：100
     * 描述：现金支付金额订单现金支付金额，详见支付金额
     * </pre>
     */
    private Integer cashFee;
    /**
     * <pre>
     * 字段名：现金支付货币类型.
     * 变量名：cash_fee_type
     * 是否必填：否
     * 类型：String(16)
     * 示例值：CNY
     * 描述：货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * </pre>
     */
    private String cashFeeType;
    /**
     * <pre>
     * 字段名：总代金券金额.
     * 变量名：coupon_fee
     * 是否必填：否
     * 类型：Int
     * 示例值：10
     * 描述：代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
     * </pre>
     */
    private Integer couponFee;

    /**
     * <pre>
     * 字段名：代金券使用数量.
     * 变量名：coupon_count
     * 是否必填：否
     * 类型：Int
     * 示例值：1
     * 描述：代金券使用数量
     * </pre>
     */
    private Integer couponCount;

    private List<WxPayOrderNotifyCoupon> couponList;

    /**
     * <pre>
     * 字段名：微信支付订单号.
     * 变量名：transaction_id
     * 是否必填：是
     * 类型：String(32)
     * 示例值：1217752501201407033233368018
     * 描述：微信支付订单号
     * </pre>
     */
    private String transactionId;

    /**
     * <pre>
     * 字段名：商户订单号.
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：String(32)
     * 示例值：1212321211201407033568112322
     * 描述：商户系统的订单号，与请求一致。
     * </pre>
     */
    private String outTradeNo;
    /**
     * <pre>
     * 字段名：商家数据包.
     * 变量名：attach
     * 是否必填：否
     * 类型：String(128)
     * 示例值：123456
     * 描述：商家数据包，原样返回
     * </pre>
     */
    private String attach;

    /**
     * <pre>
     * 字段名：支付完成时间.
     * 变量名：time_end
     * 是否必填：是
     * 类型：String(14)
     * 示例值：20141030133525
     * 描述：支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * </pre>
     */
    private String timeEnd;

    /**
     * <pre>
     * 字段名：接口版本号.
     * 变量名：version
     * 类型：String(32)
     * 示例值：1.0
     * 更多信息，详见文档：https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_101&index=1
     * </pre>
     */
    private String version;

    /**
     * <pre>
     * 字段名：汇率.
     * 变量名：rate_value
     * 类型：String(16)
     * 示例值：650000000
     * 标价币种与支付币种的兑换比例乘以10的8次方即为此值，例如美元兑换人民币的比例为6.5，则rate_value=650000000
     * </pre>
     */
    private String rateValue;

    /**
     * <pre>
     * 字段名：签名类型.
     * 变量名：sign_type
     * 类型：String(32)
     * 示例值：HMAC-SHA256
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     * </pre>
     */
    private String signType;

    @Override
    public void checkResult() throws WxPayException {
        //防止伪造成功通知
        if (WxConstant.ResultCode.SUCCESS.equals(getReturnCode()) && getSign() == null) {
            throw new WxPayException("伪造的通知！");
        }

        super.checkResult();
    }

 /* @Override
  public Map<String, String> toMap() {
    Map<String, String> resultMap = SignUtils.xmlBean2Map(this);
    if (this.getCouponCount() != null && this.getCouponCount() > 0) {
      for (int i = 0; i < this.getCouponCount(); i++) {
        WxPayOrderNotifyCoupon coupon = couponList.get(i);
        resultMap.putAll(coupon.toMap(i));
      }
    }
    return resultMap;
  }*/

    @Override
    protected void loadXML(Document d) {
        promotionDetail = readXMLString(d, "promotion_detail");
        deviceInfo = readXMLString(d, "device_info");
        openid = readXMLString(d, "openid");
        isSubscribe = readXMLString(d, "is_subscribe");
        subOpenid = readXMLString(d, "sub_openid");
        subIsSubscribe = readXMLString(d, "sub_is_subscribe");
        tradeType = readXMLString(d, "trade_type");
        bankType = readXMLString(d, "bank_type");
        totalFee = readXMLInteger(d, "total_fee");
        settlementTotalFee = readXMLInteger(d, "settlement_total_fee");
        feeType = readXMLString(d, "fee_type");
        cashFee = readXMLInteger(d, "cash_fee");
        cashFeeType = readXMLString(d, "cash_fee_type");
        couponFee = readXMLInteger(d, "coupon_fee");
        couponCount = readXMLInteger(d, "coupon_count");
        transactionId = readXMLString(d, "transaction_id");
        outTradeNo = readXMLString(d, "out_trade_no");
        attach = readXMLString(d, "attach");
        timeEnd = readXMLString(d, "time_end");
        version = readXMLString(d, "version");
        rateValue = readXMLString(d, "rate_value");
        signType = readXMLString(d, "sign_type");

        composeCoupons();
    }

    /**
     * 通过xml组装couponList属性内容.
     */
    protected void composeCoupons() {
        if (this.couponCount == null || this.couponCount == 0) {
            return;
        }
        this.couponList = new ArrayList(couponCount);
        for (int i = 0; i < this.couponCount; i++) {
            WxPayOrderNotifyCoupon coupon = new WxPayOrderNotifyCoupon();
            coupon.setCouponId(this.getXmlValue("xml/coupon_id_" + i));
            coupon.setCouponType(this.getXmlValue("xml/coupon_type_" + i));
            coupon.setCouponFee(this.getXmlValueAsInt("xml/coupon_fee_" + i));
            couponList.add(coupon);
        }
    }

}
