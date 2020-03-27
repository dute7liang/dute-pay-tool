package com.dute7liang.pay.tool.common.core;

import com.dute7liang.pay.tool.common.bean.Trade;
import com.dute7liang.pay.tool.common.bean.TradeStatus;
import com.dute7liang.pay.tool.common.bean.TradeToken;
import com.dute7liang.pay.tool.common.exception.RefundException;

/**
 * 支付定义
 * @author yangyuan
 * @date 2019/1/4 15:03
 * @param <T>
 */
public interface Pay<T extends Trade> {
    
    /**
     * 支付
     * @param trade 订单
     * @return 订单凭证
     */
    TradeToken<?> pay(T trade);

    /**
     * 退款
     * @param trade 订单
     * @throws RefundException 退款失败抛出
     */
    void refund(T trade) throws RefundException;
    
    /**
     * 订单状态查询
     * @param trade 订单
     * @return 订单状态
     */
    TradeStatus status(T trade);
    
}
