package com.dute7liang.pay.tool.vx.core.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信H5支付统一下单后发起支付拼接所需参数实现类.
 *
 * <br/>
 * author: zl
 * Date: 2020/4/1
 */
@Data
@AllArgsConstructor
public class WxPayMwebOrderResult implements Serializable {
  private static final long serialVersionUID = 8866329695767762066L;

  private String mwebUrl;
}
