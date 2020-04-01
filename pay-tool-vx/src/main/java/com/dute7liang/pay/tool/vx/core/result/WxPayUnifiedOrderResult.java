package com.dute7liang.pay.tool.vx.core.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

/**
 * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"返回的结果
 * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
 *
 * @author zl
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxPayUnifiedOrderResult extends BaseWxPayResult {

    /**
     * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    private String prepayId;

    /**
     * 交易类型，取值为：JSAPI，NATIVE，APP等
     */
    private String tradeType;

    /**
     * mweb_url 支付跳转链接
     */
    private String mwebUrl;

    /**
     * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     */
    private String codeURL;

    /**
     * 从XML结构中加载额外的熟悉
     *
     * @param d Document
     */
    @Override
    protected void loadXML(Document d) {
        prepayId = readXMLString(d, "prepay_id");
        tradeType = readXMLString(d, "trade_type");
        mwebUrl = readXMLString(d, "mweb_url");
        codeURL = readXMLString(d, "code_url");
    }

}
