package com.dute7liang.pay.tool.vx.core.trade;

import lombok.*;

import java.util.Map;

/**
 * <pre>
 *  关闭订单请求对象类
 * Created by Binary Wang on 2016-10-27.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxPayOrderCloseTrade extends WxBaseTrade {

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 二选一
   * String(32)
   * 20150806125346
   * 商户系统内部的订单号，当没提供transaction_id时需要传这个。
   * </pre>
   */
  private String outTradeNo;

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("out_trade_no", outTradeNo);
  }

}
