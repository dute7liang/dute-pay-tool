package com.dute7liang.pay.tool.vx.bean;

import com.dute7liang.pay.tool.common.config.XmlConfig;
import com.dute7liang.pay.tool.common.util.PayMD5;
import com.dute7liang.pay.tool.common.util.xml.XStreamInitializer;
import com.dute7liang.pay.tool.vx.config.WxConstant;
import com.dute7liang.pay.tool.vx.config.WxPayConfigTest;
import com.dute7liang.pay.tool.vx.util.SignUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/27 19:38
 */
@Getter
@Setter
public abstract class WxBaseTrade implements Serializable {

    /**
     * <pre>
     * 字段名：公众账号ID.
     * 变量名：appid
     * 是否必填：是
     * 类型：String(32)
     * 示例值：wxd678efh567hg6787
     * 描述：微信分配的公众账号ID（企业号corpid即为此appId）
     * </pre>
     */
    @XStreamAlias("appid")
    protected String appid;
    /**
     * <pre>
     * 字段名：商户号.
     * 变量名：mch_id
     * 是否必填：是
     * 类型：String(32)
     * 示例值：1230000109
     * 描述：微信支付分配的商户号
     * </pre>
     */
    @XStreamAlias("mch_id")
    protected String mchId;

    /**
     * <pre>
     * 字段名：签名.
     * 变量名：sign
     * 是否必填：是
     * 类型：String(32)
     * 示例值：C380BEC2BFD727A4B6845133519F3AD6
     * 描述：签名，详见签名生成算法
     * </pre>
     */
    @XStreamAlias("sign")
    protected String sign;

    /**
     * <pre>
     * 签名类型.
     * sign_type
     * 否
     * String(32)
     * HMAC-SHA256
     * 签名类型，目前支持HMAC-SHA256和MD5
     * </pre>
     */
    @XStreamAlias("sign_type")
    private String signType;

    /**
     * <pre>
     * 字段名：随机字符串.
     * 变量名：nonce_str
     * 是否必填：是
     * 类型：String(32)
     * 示例值：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     * 描述：随机字符串，不长于32位。推荐随机数生成算法
     * </pre>
     */
    @XStreamAlias("nonce_str")
    protected String nonceStr;

    /**
     * To xml string.
     *
     * @return the string
     */
    public String toXML() {
        if (XmlConfig.fastMode) {
            return toFastXml();
        }
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }

    /**
     * 使用快速算法组装xml
     *
     * @return
     */
    private String toFastXml() {
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement(xmlRootTagName());

            Map<String, String> signParams = getSignParams();
            signParams.put("sign", sign);
            for (Map.Entry<String, String> entry : signParams.entrySet()) {
                if (entry.getValue() == null) {
                    continue;
                }
                Element elm = root.addElement(entry.getKey());
                elm.addText(entry.getValue());
            }

            return document.asXML();
        } catch (Exception e) {
            throw new RuntimeException("generate xml error", e);
        }
    }

    /**
     * 返回xml结构的根节点名称
     *
     * @return 默认返回"xml", 特殊情况可以在子类中覆盖
     */
    protected String xmlRootTagName() {
        return "xml";
    }

    /**
     * 获取签名时需要的参数.
     * 注意：不含sign属性
     */
    public Map<String, String> getSignParams() {
        Map<String, String> map = new HashMap<>();
        map.put("appid", appid);
        map.put("mch_id", mchId);
        map.put("nonce_str", nonceStr);
        map.put("sign_type", signType);
        storeMap(map);
        return map;
    }


    /**
     * 将属性组装到一个Map中，供签名和最终发送XML时使用.
     * 这里需要将所有的属性全部保存进来，签名的时候会自动调用getIgnoredParamsForSign进行忽略，
     * 不用担心。否则最终生成的XML会缺失。
     *
     * @param map 传入的属性Map
     */
    abstract protected void storeMap(Map<String, String> map);


    public void checkAndSign(WxPayConfigTest config) {
        // 对一些必填参数给默认值
        if (StringUtils.isBlank(getAppid())) {
            this.setAppid(config.getAppid());
        }
        if (StringUtils.isBlank(getMchId())) {
            this.setMchId(config.getMchId());
        }
        if (StringUtils.isBlank(getSignType())) {
            this.setSignType(WxConstant.SignType.MD5);
        }
        if (StringUtils.isBlank(getNonceStr())) {
            this.setNonceStr(PayMD5.encode(UUID.randomUUID().toString()));
        }
        // 检查参数的必填项
        // this.checkFields();
        //设置签名字段的值
        this.setSign(SignUtils.createSign(this.getSignParams(), this.getSignType(), config.getMchKey(), this.getIgnoredParamsForSign()));
    }


    /**
     * 签名时，忽略的参数.
     *
     * @return the string [ ]
     */
    protected String[] getIgnoredParamsForSign() {
        return new String[0];
    }
}
