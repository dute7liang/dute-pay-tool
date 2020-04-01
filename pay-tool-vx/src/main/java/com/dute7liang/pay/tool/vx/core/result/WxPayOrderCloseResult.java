package com.dute7liang.pay.tool.vx.core.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

/**
 * 关闭订单结果对象类
 *
 * <br/>
 * author: zl
 * Date: 2020/4/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxPayOrderCloseResult extends BaseWxPayResult {

  /**
   * 业务结果描述
   */
  private String resultMsg;

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXML(Document d) {
    resultMsg = readXMLString(d, "result_msg");
  }

}
