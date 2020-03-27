package com.dute7liang.pay.tool.vx.core;


import com.dute7liang.pay.tool.vx.core.common.AbstractWxTransfer;

/**
 * 微信转账
 * @Auther: yangyuan
 * @Date: 2019/1/18 19:43
 */
public class WxTransfer extends AbstractWxTransfer {
    private static final WxTransfer WX_TRANSFER = new WxTransfer();

    private WxTransfer(){

    }

    public static WxTransfer getInstance(){
        return WX_TRANSFER;
    }

}
