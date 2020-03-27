package com.duteliang.pay.tool.ali.core.common;


import com.duteliang.pay.tool.ali.bean.AliTrade;

import java.util.Map;

/**
 * 支付宝支付业务参数封装
 * @Auther: yangyuan
 * @Date: 2019/1/15 13:51
 */
public interface AliPayBizOptions extends AliBizOptions{
    /**
     * 构造支付业务参数
     * @param trade
     */
    Map<String ,String> buildBizParams(AliTrade trade);
}
