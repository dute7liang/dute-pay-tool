package com.dute7liang.pay.tool.common.core;


import com.dute7liang.pay.tool.common.bean.TransferStatus;
import com.dute7liang.pay.tool.common.bean.TransferTrade;
import com.dute7liang.pay.tool.common.exception.TransferException;

/**
 * 转账定义
 * @author yangyuan
 * @date 2019/1/4
 */
public interface Transfer<T extends TransferTrade> {
    /**
     * 转账
     * @param transferTrade 转账订单
     */
    void transfer(T transferTrade) throws TransferException;
    
    /**
     * 订单转账状态查询
     * @param transferTrade 转账订单，包含商户订单号(非第三方订单号)
     * @return 转账状态
     */
    TransferStatus status(T transferTrade);
}
