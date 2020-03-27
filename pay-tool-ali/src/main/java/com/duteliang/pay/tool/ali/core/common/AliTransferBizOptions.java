package com.duteliang.pay.tool.ali.core.common;


import com.duteliang.pay.tool.ali.bean.AliTransferTrade;

import java.util.Map;

/**
 * 支付宝转账业务参数封装
 * @Auther: yangyuan
 * @Date: 2019/1/15 13:52
 */
public interface AliTransferBizOptions extends AliBizOptions{
    /**
     * 构造转账业务参数
     * @param aliTransferTrade
     */
    Map<String ,String> buildBizParams(AliTransferTrade aliTransferTrade);
}
