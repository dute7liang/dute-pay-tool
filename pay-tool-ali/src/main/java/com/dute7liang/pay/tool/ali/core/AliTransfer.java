package com.dute7liang.pay.tool.ali.core;


import com.dute7liang.pay.tool.ali.core.common.AbstractAliTransfer;

/**
 * 支付宝转账实现
 * @Auther: yangyuan
 * @Date: 2019/1/16 15:49
 */
public class AliTransfer extends AbstractAliTransfer {
    private static final AliTransfer ALI_TRANSFER = new AliTransfer();

    private AliTransfer(){

    }

    public static AliTransfer getInstance(){
        return ALI_TRANSFER;
    }
}
