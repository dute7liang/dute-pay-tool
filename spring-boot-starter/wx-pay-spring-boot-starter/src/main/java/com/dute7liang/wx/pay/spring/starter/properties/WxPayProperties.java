package com.dute7liang.wx.pay.spring.starter.properties;

import com.dute7liang.pay.tool.vx.constant.WxConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: zl
 * @Date: 2020-4-1 11:58
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "duteliang.wx.pay")
public class WxPayProperties {

    private static final String DEFAULT_PAY_BASE_URL = "https://api.mch.weixin.qq.com";

    /**
     * 微信支付接口请求地址域名部分.
     */
    private String payBaseUrl = DEFAULT_PAY_BASE_URL;

    /**
     * 公众账号ID
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 合作key
     */
    private String mchKey;

    /**
     * 支付回调地址
     */
    private String notifyUrl;

    /**
     * 微信支付是否使用仿真测试环境.
     * 默认不使用
     */
    private boolean useSandboxEnv = false;

    /**
     * 证书绝对路径
     */
    private String certPath;

    /**
     * 证书密码
     */
    private String certPassword;

    /**
     * 签名方式
     */
    private String signType = WxConstant.SignType.MD5;

    private boolean enabled = true;

}


