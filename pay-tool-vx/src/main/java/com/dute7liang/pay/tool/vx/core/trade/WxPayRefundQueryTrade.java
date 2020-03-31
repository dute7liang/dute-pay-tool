package com.dute7liang.pay.tool.vx.core.trade;

import com.dute7liang.pay.tool.vx.exception.WxPayException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * <pre>
 * Created by Binary Wang on 2016-11-24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxPayRefundQueryTrade extends WxBaseTrade {
  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 商户自定义的终端设备号，如门店编号、设备的ID等
   * </pre>
   */
  private String deviceInfo;

  //************以下四选一************
  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * String(32)
   * 1217752501201407033233368018
   * 微信订单号
   * </pre>
   */
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * String(32)
   * 1217752501201407033233368018
   * 商户系统内部的订单号
   * </pre>
   */
  private String outTradeNo;

  /**
   * <pre>
   * 商户退款单号
   * out_refund_no
   * String(32)
   * 1217752501201407033233368018
   * 商户侧传给微信的退款单号
   * </pre>
   */
  private String outRefundNo;

  /**
   * <pre>
   * 微信退款单号
   * refund_id
   * String(28)
   * 1217752501201407033233368018
   * 微信生成的退款单号，在申请退款接口有返回
   * </pre>
   */
  private String refundId;

  @Override
  protected void checkFields() throws WxPayException {
    if ((StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo)
      && StringUtils.isBlank(outRefundNo) && StringUtils.isBlank(refundId)) ||
      (StringUtils.isNotBlank(transactionId) && StringUtils.isNotBlank(outTradeNo)
        && StringUtils.isNotBlank(outRefundNo) && StringUtils.isNotBlank(refundId))) {
      throw new WxPayException("transaction_id，out_trade_no，out_refund_no，refund_id 必须四选一");
    }

  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("device_info", deviceInfo);
    map.put("transaction_id", transactionId);
    map.put("out_trade_no", outTradeNo);
    map.put("out_refund_no", outRefundNo);
    map.put("refund_id", refundId);
  }
}
