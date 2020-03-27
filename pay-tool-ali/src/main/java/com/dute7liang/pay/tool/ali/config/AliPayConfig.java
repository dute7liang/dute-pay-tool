package com.dute7liang.pay.tool.ali.config;


import lombok.Getter;
import lombok.Setter;

/**
 * 支付宝支付全局统一配置
 * @author yangyuan
 * @date 2017年4月28日
 */
public class AliPayConfig {

    /**
     * 应用ID
     */
    @Getter
    @Setter
    private static String appid;
    /**
     * 商户的私钥
     */
    @Setter
    @Getter
    private static String privateKey;
    /**
     * 支付回调地址
     */
    @Getter
    @Setter
    private static String notifyURL;

    /**
     * 支付宝网关
     */
    @Getter
    @Setter
    private static String gateway = "https://openapi.alipay.com/gateway.do";

    /**
     * 支付宝的公钥
     */
    @Getter
    @Setter
    private static String aliPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvsH3SyXNZNIcMiwgvzJWXvocYZwN0XHSq1ix06maJt8aJ6tTl+iG6vEkhQzIpsnZOyNW3aVaE3jEmRE51kKR81pO3Ulj3wg2wE6DufGmTobDclrjZlEJgCjINC+p10YEMo83CWhlPhtTmcY3cGqRpfBsacHXtU4vMwsR1WH3amAwKaPZVHqX0WH+mwMUW41UU1NFwOB5LqbmtnupEMwQ77M6DsxtTTXG4laQReQIvLkg8PGEm9U0jfqvLqfxFNj701BjR2BwN22jPJtCZji/tYWlUUVS2EtZVbvu8tj7hcRGyiQBvLNNhsiNfVH5UXtA6d5SuRIu+T4Sbj8yMKgbmwIDAQAB";


}
