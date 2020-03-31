package com.dute7liang.pay.tool.vx.constant;

import java.util.Arrays;
import java.util.List;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/27 20:52
 */
public class WxConstant {

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

    /**
     * 交易类型.
     */
    public static class TradeType {
        /**
         * 原生扫码支付.
         */
        public static final String NATIVE = "NATIVE";

        /**
         * App支付.
         */
        public static final String APP = "APP";

        /**
         * 公众号支付/小程序支付.
         */
        public static final String JSAPI = "JSAPI";

        /**
         * H5支付.
         */
        public static final String MWEB = "MWEB";

        /**
         * 刷卡支付.
         * 刷卡支付有单独的支付接口，不调用统一下单接口
         */
        public static final String MICROPAY = "MICROPAY";
    }

    /**
     * 交易状态.
     */
    public static class WxpayTradeStatus {
        /**
         * 支付成功.
         */
        public static final String SUCCESS = "SUCCESS";

        /**
         * 支付失败(其他原因，如银行返回失败).
         */
        public static final String PAY_ERROR = "PAYERROR";

        /**
         * 用户支付中.
         */
        public static final String USER_PAYING = "USERPAYING";

        /**
         * 已关闭.
         */
        public static final String CLOSED = "CLOSED";

        /**
         * 未支付.
         */
        public static final String NOTPAY = "NOTPAY";

        /**
         * 转入退款.
         */
        public static final String REFUND = "REFUND";

        /**
         * 已撤销（刷卡支付）.
         */
        public static final String REVOKED = "REVOKED";
    }


    /**
     * 退款资金来源.
     */
    public static class RefundAccountSource {
        /**
         * 可用余额退款/基本账户.
         */
        public static final String RECHARGE_FUNDS = "REFUND_SOURCE_RECHARGE_FUNDS";

        /**
         * 未结算资金退款.
         */
        public static final String UNSETTLED_FUNDS = "REFUND_SOURCE_UNSETTLED_FUNDS";

    }

}
