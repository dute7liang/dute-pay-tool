package com.dute7liang.pay.tool.common.bean;

/**
 * 订单凭证
 * @Auther: yangyuan
 * @Date: 2019/1/4 15:03
 */
public interface TradeToken<T> {

    /**
     * 凭证内容
     * @return
     */
    T value();

}
