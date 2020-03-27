package com.dute7liang.pay.tool.vx.config;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxPayConfig {
    /**
     * 公众账号ID
     */
    @Getter
    @Setter
    private static String appid;

    /**
     * 商户号
     */
    @Getter
    @Setter
    private static String mchid;

    /**
     * 合作key
     */
    @Getter
    @Setter
    private static String key;

    /**
     * 支付回调地址
     */
    @Getter
    @Setter
    private static String notifyURL;

    /**
     * 证书绝对路径
     */
    @Getter
    @Setter
    private static String certPath;

    /**
     * 证书密码
     */
    @Getter
    @Setter
    private static String certPassword;


    /**
     * 统一下单接口链接
     */
    @Getter
    @Setter
    private static String unifiedorderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 企业付款接口链接
     */
    @Getter
    @Setter
    private static String enterprisePayURL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 单笔订单查询接口
     */
    @Getter
    @Setter
    private static String orderQueryURL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 申请退款接口
     */
    @Getter
    @Setter
    private static String orderRefundURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 退款查询接口
     */
    @Getter
    @Setter
    private static String orderRefundQueryURL = "https://api.mch.weixin.qq.com/pay/refundquery";

    /**
     * 关闭订单接口
     */
    @Getter
    @Setter
    private static String closeOrderURL = "https://api.mch.weixin.qq.com/pay/closeorder";

    /**
     * 转账接口
     */
    @Getter
    @Setter
    private static String transfersURL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 转账查询接口
     */
    @Getter
    @Setter
    private static String transfersQueryURL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";

}
