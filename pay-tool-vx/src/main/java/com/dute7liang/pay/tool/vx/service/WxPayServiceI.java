package com.dute7liang.pay.tool.vx.service;

import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.core.notify.WxPayOrderNotifyResult;
import com.dute7liang.pay.tool.vx.core.notify.WxPayRefundNotifyResult;
import com.dute7liang.pay.tool.vx.core.result.WxPayOrderCloseResult;
import com.dute7liang.pay.tool.vx.core.result.WxPayOrderQueryResult;
import com.dute7liang.pay.tool.vx.core.result.WxPayRefundQueryResult;
import com.dute7liang.pay.tool.vx.core.result.WxPayRefundResult;
import com.dute7liang.pay.tool.vx.core.trade.WxPayRefundTrade;
import com.dute7liang.pay.tool.vx.core.trade.WxUnifiedOrderTrade;
import com.dute7liang.pay.tool.vx.exception.WxPayException;

/**
 * 微信支付统一调度器定义
 * @author zhangliang
 * @date 2020年3月17日
 */
public interface WxPayServiceI {

    /**
     * 统一下单
     * @param trade 订单
     * @return 返回给前端的数据
     */
    <T> T unifiedorder(WxUnifiedOrderTrade trade);

    /**
     * 查询订单， 参数二选一
     * @param transactionId 微信订单号
     * @param outTradeNo 系统内部订单号
     */
    WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo);

    /**
     * <pre>
     * 关闭订单.
     * 应用场景
     * 以下情况需要调用关单接口：
     * 1. 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
     * 2. 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
     * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
     * 接口地址：https://api.mch.weixin.qq.com/pay/closeorder
     * 是否需要证书：   不需要。
     * </pre>
     *
     * @param outTradeNo 商户系统内部的订单号
     * @return the wx pay order close result
     * @throws WxPayException the wx pay exception
     */
    WxPayOrderCloseResult closeOrder(String outTradeNo) throws WxPayException;

    /**
     * <pre>
     * 微信支付-申请退款.
     * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
     * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
     * </pre>
     *
     * @param request 请求对象
     * @return 退款操作结果 wx pay refund result
     * @throws WxPayException the wx pay exception
     */
    WxPayRefundResult refund(WxPayRefundTrade request) throws WxPayException;

    /**
     * <pre>
     * 微信支付-查询退款.
     * 应用场景：
     *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，
     *  银行卡支付的退款3个工作日后重新查询退款状态。
     * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5
     * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
     * </pre>
     * 以下四个参数四选一
     *
     * @param transactionId 微信订单号
     * @param outTradeNo    商户订单号
     * @param outRefundNo   商户退款单号
     * @param refundId      微信退款单号
     * @return 退款信息 wx pay refund query result
     * @throws WxPayException the wx pay exception
     */
    WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId)
            throws WxPayException;

    /**
     * 解析支付结果通知.
     * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
     *
     * @param xmlData the xml data
     * @return the wx pay order notify result
     * @throws WxPayException the wx pay exception
     */
    WxPayOrderNotifyResult parseOrderNotifyResult(String xmlData) throws WxPayException;

    /**
     * 解析退款结果通知
     * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_16&index=9
     *
     * @param xmlData the xml data
     * @return the wx pay refund notify result
     * @throws WxPayException the wx pay exception
     */
    WxPayRefundNotifyResult parseRefundNotifyResult(String xmlData) throws WxPayException;

    /**
     * 获取配置.
     *
     * @return the config
     */
    WxPayConfig getConfig();

    /**
     * 设置配置对象.
     *
     * @param config the config
     */
    void setConfig(WxPayConfig config);


}
