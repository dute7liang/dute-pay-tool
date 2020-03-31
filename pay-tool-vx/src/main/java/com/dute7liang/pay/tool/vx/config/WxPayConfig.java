package com.dute7liang.pay.tool.vx.config;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class WxPayConfig {

    private static final String DEFAULT_PAY_BASE_URL = "https://api.mch.weixin.qq.com";

    /**
     * 微信支付接口请求地址域名部分.
     */
    private String payBaseUrl = DEFAULT_PAY_BASE_URL;

    /**
     * 公众账号ID
     */
    private String appid;

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
    private String notifyURL;

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
     * 返回所设置的微信支付接口请求地址域名.
     * @return 微信支付接口请求地址域名
     */
    public String getPayBaseUrl() {
        if (StringUtils.isEmpty(this.payBaseUrl)) {
            return DEFAULT_PAY_BASE_URL;
        }

        return this.payBaseUrl;
    }



}
