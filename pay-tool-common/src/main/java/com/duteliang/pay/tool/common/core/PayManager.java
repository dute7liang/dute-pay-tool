package com.duteliang.pay.tool.common.core;

import com.duteliang.pay.tool.common.bean.Trade;
import com.duteliang.pay.tool.common.bean.TradeStatus;
import com.duteliang.pay.tool.common.bean.TradeToken;
import com.duteliang.pay.tool.common.exception.RefundException;

/**
 * 支付统一调度器定义
 * @author yangyuan
 * @date 2017年5月9日
 */
public interface PayManager {
    /**
     * 统一移动支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> mobilePay(Trade trade);
    
    /**
     * 统一电脑网页支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> webPcPay(Trade trade);

    /**
     * 统一手机网页支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> webMobilePay(Trade trade);

    /**
     * 统一扫码支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> qrcodePay(Trade trade);

    /**
     * 统一JS-SDK支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> jsSdkPay(Trade trade);

    /**
     * 统一退款
     * @param trade 订单
     * @return 退款信息
     */
    void refund(Trade trade) throws RefundException;
    
    /**
     * 统一状态查询
     * @param trade 订单
     * @return 订单状态
     */
    TradeStatus status(Trade trade);
}
