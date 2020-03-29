package com.dute7liang.pay.tool.vx.manager;

import com.dute7liang.pay.tool.common.bean.TradeStatus;
import com.dute7liang.pay.tool.common.bean.TradeToken;
import com.dute7liang.pay.tool.common.exception.RefundException;
import com.dute7liang.pay.tool.vx.bean.WxTrade;
import com.dute7liang.pay.tool.vx.config.WxPayConfigTest;

/**
 * 微信支付统一调度器定义
 * @author zhangliang
 * @date 2020年3月17日
 */
public interface WxPayServiceI {
    /**
     * 统一移动支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> mobilePay(WxTrade trade);
    
    /**
     * 统一电脑网页支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> webPcPay(WxTrade trade);

    /**
     * 统一手机网页支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> webMobilePay(WxTrade trade);

    /**
     * 统一扫码支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> qrcodePay(WxTrade trade);

    /**
     * 统一JS-SDK支付
     * @param trade 订单
     * @return 订单凭证
     */
    <T> TradeToken<T> jsSdkPay(WxTrade trade);

    /**
     * 统一退款
     * @param trade 订单
     * @return 退款信息
     */
    void refund(WxTrade trade) throws RefundException;
    
    /**
     * 统一状态查询
     * @param trade 订单
     * @return 订单状态
     */
    TradeStatus status(WxTrade trade);

    /**
     * 获取配置.
     *
     * @return the config
     */
    WxPayConfigTest getConfig();
}
