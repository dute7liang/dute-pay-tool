package com.dute7liang.pay.tool.vx.core.trade;

import com.dute7liang.pay.tool.vx.constant.WxConstant;
import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.exception.WxPayException;
import lombok.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * <pre>
 * 微信支付-申请退款请求参数
 * </pre>
 *
 * <br/>
 * author: zl
 * Date: 2020/4/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxPayRefundTrade extends WxBaseTrade {
  private static final String[] REFUND_ACCOUNT = new String[]{
    WxConstant.RefundAccountSource.RECHARGE_FUNDS, WxConstant.RefundAccountSource.UNSETTLED_FUNDS};

  /**
   * <pre>
   * 字段名：设备号.
   * 变量名：device_info
   * 是否必填：否
   * 类型：String(32)
   * 示例值：13467007045764
   * 描述：终端设备号
   * </pre>
   */
  private String deviceInfo;
  /**
   * <pre>
   * 字段名：微信订单号.
   * 变量名：transaction_id
   * 是否必填：跟out_trade_no二选一
   * 类型：String(28)
   * 示例值：1217752501201400000000000000
   * 描述：微信生成的订单号，在支付通知中有返回
   * </pre>
   */
  private String transactionId;
  /**
   * <pre>
   * 字段名：商户订单号.
   * 变量名：out_trade_no
   * 是否必填：跟transaction_id二选一
   * 类型：String(32)
   * 示例值：1217752501201400000000000000
   * 描述：商户侧传给微信的订单号
   * </pre>
   */
  private String outTradeNo;
  /**
   * <pre>
   * 字段名：商户退款单号.
   * 变量名：out_refund_no
   * 是否必填：是
   * 类型：String(32)
   * 示例值：1217752501201400000000000000
   * 描述：商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
   * </pre>
   */
  private String outRefundNo;
  /**
   * <pre>
   * 字段名：订单金额.
   * 变量名：total_fee
   * 是否必填：是
   * 类型：Int
   * 示例值：100
   * 描述：订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  private Integer totalFee;
  /**
   * <pre>
   * 字段名：退款金额.
   * 变量名：refund_fee
   * 是否必填：是
   * 类型：Int
   * 示例值：100
   * 描述：退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  private Integer refundFee;
  /**
   * <pre>
   * 字段名：货币种类.
   * 变量名：refund_fee_type
   * 是否必填：否
   * 类型：String(8)
   * 示例值：CNY
   * 描述：货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   */
  private String refundFeeType;
  /**
   * <pre>
   * 字段名：操作员.
   * 变量名：op_user_id
   * 是否必填：是
   * 类型：String(32)
   * 示例值：1900000109
   * 描述：操作员帐号, 默认为商户号
   * </pre>
   */
  private String opUserId;
  /**
   * <pre>
   * 字段名：退款资金来源.
   * 变量名：refund_account
   * 是否必填：否
   * 类型：String(30)
   * 示例值：REFUND_SOURCE_RECHARGE_FUNDS
   * 描述：仅针对老资金流商户使用，
   * <li>REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款），
   * <li>REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
   * </pre>
   */
  private String refundAccount;

  /**
   * <pre>
   * 字段名：退款原因.
   * 变量名：refund_account
   * 是否必填：否
   * 类型：String(80)
   * 示例值：商品已售完
   * 描述：若商户传入，会在下发给用户的退款消息中体现退款原因
   * </pre>
   */
  private String refundDesc;

  /**
   * <pre>
   * 字段名：退款结果通知url.
   * 变量名：notify_url
   * 是否必填：否
   * 类型：String(256)
   * 示例值：https://weixin.qq.com/notify/
   * 描述：	异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
   * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
   * </pre>
   */
  private String notifyUrl;

  @Override
  public void checkAndSign(WxPayConfig config) {
    if (StringUtils.isBlank(this.getOpUserId())) {
      this.setOpUserId(config.getMchId());
    }

    super.checkAndSign(config);
  }

  @Override
  protected void checkFields() throws WxPayException {
    if (StringUtils.isNotBlank(this.getRefundAccount())) {
      if (!ArrayUtils.contains(REFUND_ACCOUNT, this.getRefundAccount())) {
        throw new WxPayException(
          String.format("refund_account目前必须为%s其中之一,实际值：%s", Arrays.toString(REFUND_ACCOUNT), this.getRefundAccount()));
      }
    }

    if (StringUtils.isBlank(this.getOutTradeNo()) && StringUtils.isBlank(this.getTransactionId())) {
      throw new WxPayException("transaction_id 和 out_trade_no 不能同时为空，必须提供一个");
    }
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("device_info", deviceInfo);
    map.put("transaction_id", transactionId);
    map.put("out_trade_no", outTradeNo);
    map.put("out_refund_no", outRefundNo);
    map.put("total_fee", totalFee.toString());
    map.put("refund_fee", refundFee.toString());
    map.put("refund_fee_type", refundFeeType);
    map.put("op_user_id", opUserId);
    map.put("refund_account", refundAccount);
    map.put("refund_desc", refundDesc);
    map.put("notify_url", notifyUrl);
  }

}
