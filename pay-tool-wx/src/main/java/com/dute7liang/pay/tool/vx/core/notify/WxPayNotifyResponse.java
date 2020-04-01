package com.dute7liang.pay.tool.vx.core.notify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 微信支付订单和退款的异步通知共用的响应类.
 *
 * @author someone
 */
public class WxPayNotifyResponse {
    private static final String FAIL = "FAIL";
    private static final String SUCCESS = "SUCCESS";

    /**
     * Fail string.
     *
     * @param msg the msg
     * @return the string
     */
    public static String fail(String msg) {
        Document responseDocument = DocumentHelper.createDocument();
        Element responseRootElement = responseDocument.addElement("xml");
        responseRootElement.addElement("return_code").addCDATA(FAIL);
        responseRootElement.addElement("return_msg").addCDATA(msg);
        return responseDocument.asXML();
    }

    /**
     * Success string.
     *
     * @return the string
     */
    public static String success() {
        Document responseDocument = DocumentHelper.createDocument();
        Element responseRootElement = responseDocument.addElement("xml");
        responseRootElement.addElement("return_code").addCDATA(SUCCESS);
        responseRootElement.addElement("return_msg").addCDATA("OK");
        return responseDocument.asXML();
    }

}
