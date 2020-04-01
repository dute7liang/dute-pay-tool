package com.dute7liang.pay.tool.vx.core.result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信公众号支付进行统一下单后组装所需参数的类
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
 *
 * author: zl
 * Date: 2020/4/1
 */
@Data
@Builder
public class WxPayMpOrderResult implements Serializable {
  private static final long serialVersionUID = -7966682379048446567L;

  private String appId;
  private String timeStamp;
  private String nonceStr;
  /**
   * 由于package为java保留关键字，因此改为packageValue. 前端使用时记得要更改为package
   */
  private String packageValue;
  private String signType;
  private String paySign;
}
