package com.dute7liang.pay.tool.vx.core.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信扫码支付统一下单后发起支付拼接所需参数实现类
 *
 * author: zl
 * Date: 2020/4/1
 */
@Data
@AllArgsConstructor
public class WxPayNativeOrderResult implements Serializable {
  private static final long serialVersionUID = 887792717425241444L;

  private String codeUrl;
}
