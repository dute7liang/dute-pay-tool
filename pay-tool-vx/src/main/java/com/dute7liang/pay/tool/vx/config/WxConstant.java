package com.dute7liang.pay.tool.vx.config;

import java.util.Arrays;
import java.util.List;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/27 20:52
 */
public class WxConstant {

    /**
     * 统一下单接口链接
     */
    public static String unifiedorderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 企业付款接口链接
     */
    public static String enterprisePayURL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 单笔订单查询接口
     */
    public static String orderQueryURL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 申请退款接口
     */
    public static String orderRefundURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 退款查询接口
     */
    public static String orderRefundQueryURL = "https://api.mch.weixin.qq.com/pay/refundquery";

    /**
     * 关闭订单接口
     */
    public static String closeOrderURL = "https://api.mch.weixin.qq.com/pay/closeorder";

    /**
     * 转账接口
     */
    public static String transfersURL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 转账查询接口
     */
    public static String transfersQueryURL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";

    /**
     * 签名类型.
     */
    public static class SignType {
        /**
         * The constant HMAC_SHA256.
         */
        public static final String HMAC_SHA256 = "HMAC-SHA256";
        /**
         * The constant MD5.
         */
        public static final String MD5 = "MD5";
        /**
         * The constant ALL_SIGN_TYPES.
         */
        public static final List<String> ALL_SIGN_TYPES = Arrays.asList(HMAC_SHA256, MD5);
    }

    /**
     * 业务结果代码.
     */
    public static class ResultCode {
        /**
         * 成功.
         */
        public static final String SUCCESS = "SUCCESS";

        /**
         * 失败.
         */
        public static final String FAIL = "FAIL";
    }

}
