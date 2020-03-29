package com.dute7liang.pay.tool.vx.config;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxPayConfigTest {
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
     * 证书绝对路径
     */
    private String certPath;

    /**
     * 证书密码
     */
    private String certPassword;



}
