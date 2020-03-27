package com.duteliang.pay.tool.vx.manager;

import com.duteliang.pay.tool.common.bean.Trade;
import com.duteliang.pay.tool.common.bean.TradeStatus;
import com.duteliang.pay.tool.common.bean.TradeToken;
import com.duteliang.pay.tool.common.core.PayManager;
import com.duteliang.pay.tool.common.exception.RefundException;
import com.duteliang.pay.tool.vx.bean.WxTrade;
import com.duteliang.pay.tool.vx.core.*;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/19 22:26
 */
public class VxPayManager implements PayManager {

    /**
     * 支付业务核心
     * <br>
     * 这里之所以用静态，是为了减少内存占用，这些核心类线程安全，在内存中只保留一份即可，即永久代中。
     */
    private static final WxMobilePay wxMobilePay = new WxMobilePay();
    private static final WxWebPcPay wxWebPcPay = new WxWebPcPay();
    private static final WxWebMobilePay wxWebMobilePay = new WxWebMobilePay();
    private static final WxQrcodePay wxQrcodePay = new WxQrcodePay();
    private static final WxJsSdkPay wxJsSdkPay = new WxJsSdkPay();

    @Override
    public <T> TradeToken<T>  mobilePay(Trade trade) {
        if(trade instanceof WxTrade){
            return (TradeToken<T>) wxMobilePay.pay((WxTrade) trade);
        }
        throw new IllegalArgumentException("[移动支付]不支持的订单类型[" + trade.getClass().getSimpleName() + "]");
    }

    @Override
    public <T> TradeToken<T> webPcPay(Trade trade) {
        if(trade instanceof WxTrade){
            return (TradeToken<T>) wxWebPcPay.pay((WxTrade) trade);
        }

        throw new IllegalArgumentException("[电脑网页支付]不支持的订单类型[" + trade.getClass().getSimpleName() + "]");
    }

    @Override
    public <T> TradeToken<T> webMobilePay(Trade trade) {
        if(trade instanceof WxTrade){
            return (TradeToken<T>) wxWebMobilePay.pay((WxTrade) trade);
        }
        throw new IllegalArgumentException("[手机网页支付]不支持的订单类型[" + trade.getClass().getSimpleName() + "]");
    }

    @Override
    public <T> TradeToken<T> qrcodePay(Trade trade) {
        if(trade instanceof WxTrade){
            return (TradeToken<T>) wxQrcodePay.pay((WxTrade) trade);
        }
        throw new IllegalArgumentException("[扫码支付]不支持的订单类型[" + trade.getClass().getSimpleName() + "]");
    }

    @Override
    public <T> TradeToken<T> jsSdkPay(Trade trade) {
        if(trade instanceof WxTrade){
            return (TradeToken<T>) wxJsSdkPay.pay((WxTrade) trade);
        }
        throw new IllegalArgumentException("[JS-SDK支付]不支持的订单类型[" + trade.getClass().getSimpleName() + "]");
    }

    @Override
    public void refund(Trade trade) throws RefundException {
        if(trade instanceof WxTrade){
            wxMobilePay.refund((WxTrade) trade);
            return;
        }
        throw new IllegalArgumentException("[退款]不支持的订单类型[" + trade.getClass().getSimpleName() + "]");
    }

    @Override
    public TradeStatus status(Trade trade) {
        if(trade instanceof WxTrade){
            return wxMobilePay.status((WxTrade) trade);
        }
        throw new IllegalArgumentException("[订单状态查询]不支持的订单类型[" + trade.getClass().getSimpleName() + "]");
    }

}
