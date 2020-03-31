package com.dute7liang.pay.tool.vx.core.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 微信支付-申请退款返回结果.
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 * </pre>
 *
 * @author liukaitj & Binary Wang
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxPayRefundResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = -3392333879907788033L;
  /**
   * 微信订单号.
   */
  private String transactionId;

  /**
   * 商户订单号.
   */
  private String outTradeNo;

  /**
   * 商户退款单号.
   */
  private String outRefundNo;

  /**
   * 微信退款单号.
   */
  private String refundId;

  /**
   * 退款金额.
   */
  private Integer refundFee;

  /**
   * 应结退款金额.
   */
  private Integer settlementRefundFee;

  /**
   * 标价金额.
   */
  private Integer totalFee;

  /**
   * 应结订单金额.
   */
  private Integer settlementTotalFee;

  /**
   * 标价币种.
   */
  private String feeType;

  /**
   * 现金支付金额.
   */
  private Integer cashFee;

  /**
   * 现金支付币种.
   */
  private String cashFeeType;

  /**
   * 现金退款金额，单位为分，只能为整数，详见支付金额.
   */
  private Integer cashRefundFee;

  /**
   * 退款代金券使用数量.
   */
  private Integer couponRefundCount;

  /**
   * <pre>
   * 字段名：代金券退款总金额.
   * 变量名：coupon_refund_fee
   * 是否必填：否
   * 类型：Int
   * 示例值：100
   * 描述：代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
   * </pre>
   */
  private Integer couponRefundFee;

  private List<WxPayRefundCouponInfo> refundCoupons;

  /**
   * 组装生成退款代金券信息.
   */
  public void composeRefundCoupons() {
    List<WxPayRefundCouponInfo> coupons = new ArrayList<>();
    Integer refundCount = this.getCouponRefundCount();
    if (refundCount == null) {
      //无退款代金券信息
      return;
    }

    for (int i = 0; i < refundCount; i++) {
      coupons.add(
        new WxPayRefundCouponInfo(
          this.getXmlValue("xml/coupon_refund_id_" + i),
          this.getXmlValueAsInt("xml/coupon_refund_fee_" + i),
          this.getXmlValue("xml/coupon_type_" + i)
        )
      );
    }

    this.setRefundCoupons(coupons);
  }

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXML(Document d) {
    transactionId = readXMLString(d, "transaction_id");
    outTradeNo = readXMLString(d, "out_trade_no");
    outRefundNo = readXMLString(d, "out_refund_no");
    refundId = readXMLString(d, "refund_id");
    refundFee = readXMLInteger(d, "refund_fee");
    settlementRefundFee = readXMLInteger(d, "settlement_refund_fee");
    totalFee = readXMLInteger(d, "total_fee");
    settlementTotalFee = readXMLInteger(d, "settlement_total_fee");
    feeType = readXMLString(d, "fee_type");
    cashFee = readXMLInteger(d, "cash_fee");
    cashFeeType = readXMLString(d, "cash_fee_type");
    cashRefundFee = readXMLInteger(d, "cash_refund_fee");
    couponRefundCount = readXMLInteger(d, "coupon_refund_count");
    couponRefundFee = readXMLInteger(d, "coupon_refund_fee");
  }

}
